package lilee.hd.anotterredditapp.reddit;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import lilee.hd.anotterredditapp.database.OtterDatabase;
import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_CODE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.OAUTH_BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.OAUTH_URL_ACCESS;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.REFRESH_TOKEN_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.TAG_TOKEN;

public class RedditNetworking {

    private static final String TAG = "RedditNetworking";
    TokenResponse tokenResponse;
    private RedditAPI redditAPI;
    private Context context;
    private String time = "new";

    private OtterDatabase database;

    public RedditNetworking() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        redditAPI = retrofit.create(RedditAPI.class);
    }
    private void initRetrofitOAUTH() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OAUTH_URL_ACCESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        redditAPI = retrofit.create(RedditAPI.class);
    }
    public void searchCall(String result) {
        Call<Feed> call = redditAPI.searchPost(result, time);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d("searchCall", "onResponse: Server Response" + response.toString());
                Log.d("searchCall", "onResponse: received information" + response.body().toString());

                List<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d("searchCall", "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e("searchCall", "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void redditCall() {
        Call<Feed> call = redditAPI.getHomeFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());
                if (response.isSuccessful()){
                    response.body().getData().getChildren().toString();
                List<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {
//                    Post info = childrenArrayList.get(i).getData()();
//                    String title = info.getTitle();
                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                    Log.d(TAG, "onResponse: redditCall");
                }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void otterCall() {
        Call<Feed> call = redditAPI.getOtterFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());

                List<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getImageUrl() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
//                TODO: EXCEPTIONS
            }
        });
    }

    /**
     *
     * @return
     */
    public String setUrlRequest() {
        Uri baseUri = Uri.parse(OAUTH_BASE_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID);
        builder.appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE);
        builder.appendQueryParameter(STATE_KEY, STATE);
        builder.appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI);
        builder.appendQueryParameter(DURATION_KEY, DURATION);
        builder.appendQueryParameter(SCOPE_KEY, SCOPE);
        String url = builder.toString();
        Uri.parse(url);
        return url;
    }

    public void catchAccessToken(Uri uri) {
        OkHttpClient.Builder client;
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            final String code = uri.getQueryParameter("code");
            Log.v(TAG_TOKEN, "onResume: URI received " + uri.toString());

            client = new OkHttpClient.Builder();
            client.addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {

                    Request request = chain.request();
                    String credentials = Credentials.basic(CLIENT_ID, "");
                    Request.Builder newRequest = request.newBuilder()
                            .addHeader(AUTHORIZATION_KEY, credentials);
                    return chain.proceed(newRequest.build());
                }
            });
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RedditAPI redditAPI = retrofit.create(RedditAPI.class);
            Call<TokenResponse> call = redditAPI.getAccessToken(AUTHORIZATION_CODE_KEY, code, REDIRECT_URI);
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(@NotNull Call<TokenResponse> call, @NotNull Response<TokenResponse> response) {
//                    Account account = database.accountDao().getCurrentAccount();
                    tokenResponse = response.body();

                    if (tokenResponse != null) {
                        String accessToken = tokenResponse.getAccessToken();
                        String tokenType = tokenResponse.getTokenType();
                        String expiresIn = String.valueOf(tokenResponse.getExpiresIn());
                        String scope = tokenResponse.getScope();
                        String refreshToken = tokenResponse.getRefreshToken();
                        Log.d(TAG, "onResponse: Token object" + accessToken + tokenType + expiresIn + expiresIn + scope + refreshToken);
                    }

                    Log.d(TAG, "code: " + code);
                    Log.d(TAG, "onResponse: Server Response: " + response.body().toString());
                    Log.d(TAG, "onResponse: Server Response: " + response.body().getAccessToken());
                    if (tokenResponse.getExpiresIn() ==0){
                        refreshToken(tokenResponse);
                        Log.d(TAG, "onResponse: refresh token" + tokenResponse.getAccessToken());
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ERROR: " + t.getMessage());
                }
            });
        }
    }

    public void refreshToken(TokenResponse tokenResponse) {
        OkHttpClient.Builder client;
        if (tokenResponse != null) {
            final String refreshToken = tokenResponse.getRefreshToken();
            final String expireIn = String.valueOf(tokenResponse.getExpiresIn());
            Log.d(TAG, "refreshToken: " + refreshToken + expireIn);
            client = new OkHttpClient.Builder();
            client.addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {

                    Request request = chain.request();
                    String credentials = Credentials.basic(CLIENT_ID, "");
                    Request.Builder newRequest = request.newBuilder()
                            .addHeader(AUTHORIZATION_KEY, credentials);
                    return chain.proceed(newRequest.build());
                }
            });
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RedditAPI redditAPI = retrofit.create(RedditAPI.class);

            if (refreshToken != null) {
                Call<TokenResponse> call = redditAPI.getRefreshToken(REFRESH_TOKEN_KEY, refreshToken);
                call.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        Log.d(TAG, "refresh_token: ");
                        Log.d(TAG, "onResponse: Server Response" + response.body().toString());
                        Log.d(TAG, "onResponse: Server Response" + response.body().getAccessToken());
                    }
                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: ERROR: " + t.getMessage());
                    }
                });
            }
        }else {
//            TODO do something 401
            Log.d(TAG, "refreshToken: AccessToken not found, please try logging in again");
        }
    }

//    public void userFeed() {
//        Account account = database.accountDao().getCurrentAccount();
//        OkHttpClient.Builder client;
//        if (account!= null) {
//
//            client = new OkHttpClient.Builder();
//            client.addInterceptor(new Interceptor() {
//                @NotNull
//                @Override
//                public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//
//                    Request request = chain.request();
//                    String bearer = account.getAccessToken();
//                    Request.Builder newRequest = request.newBuilder()
//                            .addHeader(AUTHORIZATION_BEARER, bearer);
//                    return chain.proceed(newRequest.build());
//                }
//            });
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            RedditAPI redditAPI = retrofit.create(RedditAPI.class);
//            Call<Subreddit> call = redditAPI.getSubscribedThing();
//            call.enqueue(new Callback<Subreddit>() {
//                @Override
//                public void onResponse(@NotNull Call<Subreddit> call, @NotNull Response<Subreddit> response) {
//
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
////                    Log.d(TAG, "code: " + code);
//                    Log.d(TAG, "onResponse: Server Response: " + response.body().toString());
//                    Log.d(TAG, "onResponse: Server Response: " + response.body().getName());
//                }
//
//                @Override
//                public void onFailure(Call<Subreddit> call, Throwable t) {
//                    Log.e(TAG, "onFailure: ERROR: " + t.getMessage());
//                }
//            });
//
//        }
//    }
//    public void getAuthenticator(){
//    }
    }

