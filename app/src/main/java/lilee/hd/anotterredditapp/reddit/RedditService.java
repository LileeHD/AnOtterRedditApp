package lilee.hd.anotterredditapp.reddit;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lilee.hd.anotterredditapp.database.OtterDatabase;
import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_BEARER;
import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID;
import static lilee.hd.anotterredditapp.reddit.Constants.OAUTH_URL_ACCESS;

public class RedditService {
    TokenResponse tokenResponse;
    private Account account;
    private OtterDatabase database;



    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    //    ----------------------------------------OAUTH-------------------------------

    //    public TokenResponse getTokenResponse() {
//        return tokenResponse;
//    }

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();
                String credentials = Credentials.basic(CLIENT_ID, "");
                Request.Builder newRequest = request.newBuilder()
                        .addHeader(AUTHORIZATION_KEY, credentials);
                return chain.proceed(newRequest.build());
            });

    private static Retrofit retrofitAouth = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //    ----------------------------------------ACCOUNT INFO-------------------------------
    private static OkHttpClient.Builder infoOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
//                Account account = database.accountDao().getCurrentAccount();
                Request request = chain.request();
//                String bearer = account.getAccessToken();
                String bearer = "";
                Request.Builder newRequest = request.newBuilder()
                        .addHeader(AUTHORIZATION_BEARER, bearer);
                return chain.proceed(newRequest.build());
            });
    private static Retrofit infoRetrofitAouth = new Retrofit.Builder()
            .baseUrl(OAUTH_URL_ACCESS)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //    ----------------------------------------REFRESH TOKEN-------------------------------

    private static Retrofit retrofitRefreshToken = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
