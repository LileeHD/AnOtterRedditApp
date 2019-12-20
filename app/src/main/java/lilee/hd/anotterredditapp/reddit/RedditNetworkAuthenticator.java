//package lilee.hd.anotterredditapp.reddit;
//
//import android.net.Uri;
//import android.util.Log;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import lilee.hd.anotterredditapp.database.OtterDatabase;
//import lilee.hd.anotterredditapp.model.account.Account;
//import lilee.hd.anotterredditapp.model.token.TokenResponse;
//import okhttp3.Authenticator;
//import okhttp3.Credentials;
//import okhttp3.Headers;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.Route;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import static lilee.hd.anotterredditapp.reddit.Constants.ACCESS_TOKEN_KEY;
//import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_BEARER;
//import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_CODE_KEY;
//import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_KEY;
//import static lilee.hd.anotterredditapp.reddit.Constants.BASE_URL;
//import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID;
//import static lilee.hd.anotterredditapp.reddit.Constants.GRANT_TYPE_KEY;
//import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI;
//import static lilee.hd.anotterredditapp.reddit.Constants.REFRESH_TOKEN_KEY;
//import static lilee.hd.anotterredditapp.reddit.Constants.TAG_TOKEN;
//import static lilee.hd.anotterredditapp.reddit.Constants.getHttpBasicAuthHeader;
//import static lilee.hd.anotterredditapp.reddit.Constants.getOAuthHeader;
//
//public class RedditNetworkAuthenticator implements Authenticator {
//
//    private Retrofit mRetrofit;
//    private OtterDatabase mDatabase;
//    private RedditNetworking mNetworking;
//    TokenResponse tokenResponse;
//
//    private static final String TAG = "RedditNetworkAuthentica";
//
//    public RedditNetworkAuthenticator(Retrofit mRetrofit, OtterDatabase mDatabase) {
//        this.mRetrofit = mRetrofit;
//        this.mDatabase = mDatabase;
//    }
//
//    @Nullable
//    @Override
//    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
//        if (response.code() == 401) {
//            String accessToken = Objects.requireNonNull(response.request().header(AUTHORIZATION_KEY)).substring(AUTHORIZATION_BEARER.length());
//            synchronized (this) {
//                Account account = mDatabase.accountDao().getCurrentAccount();
//                TokenResponse tokenResponse = mDatabase.tokenDao().getcurrentToken();
//                if (tokenResponse == null) {
//                    return null;
//                }
//                String accessTokenFromDatabase = tokenResponse.getAccessToken();
//                if (accessToken.equals(accessTokenFromDatabase)) {
//                    String newAccessToken = refreshAccessToken(tokenResponse);
//                    if (!newAccessToken.equals("")) {
//                        return response.request().newBuilder().headers(Headers.of(getOAuthHeader(newAccessToken))).build();
//                    } else {
//                        return null;
//                    }
//                } else {
//                    return response.request().newBuilder().headers(Headers.of(getOAuthHeader(accessTokenFromDatabase))).build();
//                }
//            }
//        }
//        return null;
//    }
//    public void catchAccessToken(Uri uri) {
//        OkHttpClient.Builder client;
//        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
//            final String code = uri.getQueryParameter("code");
//            Log.v(TAG_TOKEN, "onResume: URI received " + uri.toString());
//
//            client = new OkHttpClient.Builder();
//            client.addInterceptor(new Interceptor() {
//                @NotNull
//                @Override
//                public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//
//                    Request request = chain.request();
//                    String credentials = Credentials.basic(CLIENT_ID, "");
//                    Request.Builder newRequest = request.newBuilder()
//                            .addHeader(AUTHORIZATION_KEY, credentials);
//                    return chain.proceed(newRequest.build());
//                }
//            });
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            RedditAPI redditAPI = retrofit.create(RedditAPI.class);
//            Call<TokenResponse> call = redditAPI.getAccessToken(AUTHORIZATION_CODE_KEY, code, REDIRECT_URI);
//            call.enqueue(new Callback<TokenResponse>() {
//                @Override
//                public void onResponse(@NotNull Call<TokenResponse> call, @NotNull retrofit2.Response<TokenResponse> response) {
//                    Account account = mDatabase.accountDao().getCurrentAccount();
//                    tokenResponse = response.body();
//
//                    if (tokenResponse != null) {
//                        String accessToken = tokenResponse.getAccessToken();
//                        String tokenType = tokenResponse.getTokenType();
//                        String expiresIn = String.valueOf(tokenResponse.getExpiresIn());
//                        String scope = tokenResponse.getScope();
//                        String refreshToken = tokenResponse.getRefreshToken();
//                        Log.d(TAG, "onResponse: Token object" + accessToken + tokenType + expiresIn + expiresIn + scope + refreshToken);
//                    }
//
//                    Log.d(TAG, "code: " + code);
//                    Log.d(TAG, "onResponse: Server Response: " + response.body().toString());
//                    Log.d(TAG, "onResponse: Server Response: " + response.body().getAccessToken());
//                    if (response.code() == 401){
//                        refreshAccessToken(tokenResponse);
//                        Log.d(TAG, "onResponse: refresh token" + tokenResponse.getAccessToken());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<TokenResponse> call, Throwable t) {
//                    Log.e(TAG, "onFailure: ERROR: " + t.getMessage());
//                }
//            });
//        }
//    }
//
//    private String refreshAccessToken(TokenResponse tokenResponse) {
//        Account account = mDatabase.accountDao().getCurrentAccount();
//        String refreshToken = mDatabase.tokenDao().getcurrentToken().getRefreshToken();
//
//        RedditAPI api = mRetrofit.create(RedditAPI.class);
//
//        Map<String, String> params = new HashMap<>();
//        params.put(GRANT_TYPE_KEY, Constants.GRANT_TYPE_REFRESH_TOKEN);
//        params.put(REFRESH_TOKEN_KEY, refreshToken);
//
//        Call<String> accessTokenCall = api.getAccessTokenAuth(getHttpBasicAuthHeader(), params);
//        try {
//            retrofit2.Response response = accessTokenCall.execute();
//            if (response.isSuccessful() && response.body() != null) {
//                JSONObject jsonObject = new JSONObject((String) response.body());
//                String newAccessToken = jsonObject.getString(ACCESS_TOKEN_KEY);
//                mDatabase.accountDao().changeAccessToken(account.getUsername(), newAccessToken);
//
//                return newAccessToken;
//            }
//            return "";
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
//}
