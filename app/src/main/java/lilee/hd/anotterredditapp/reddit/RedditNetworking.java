package lilee.hd.anotterredditapp.reddit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import lilee.hd.anotterredditapp.reddit.model.Feed;
import lilee.hd.anotterredditapp.reddit.model.TokenResponse;
import lilee.hd.anotterredditapp.reddit.model.post.Children;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static lilee.hd.anotterredditapp.reddit.Constants.BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.OAUTH_BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.TAG_TOKEN;

public class RedditNetworking {
    private static final String TAG = "RedditNetworking";
    private static RedditNetworking session;
    private RedditAPI redditAPI;
    private Context context;
    private String time = "new";

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

    //    public static RedditNetworking getInstance(){
//        if (session == null){
//            session = new RedditNetworking();
//        }
//        return session;
//    }
//
//    RedditAPI getAPI(){
//        return redditAPI;
//    }

    public void searchCall(String result) {
        Call<Feed> call = redditAPI.searchPost(result, time);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d("searchCall", "onResponse: Server Response" + response.toString());
                Log.d("searchCall", "onResponse: received information" + response.body().toString());

                ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d("searchCall", "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "url: " + childrenArrayList.get(i).getData().getPostUrl() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getThumbnail() + "\n" +
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

                ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "url: " + childrenArrayList.get(i).getData().getPostUrl() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getThumbnail() + "\n" +
                            "reddit_video: " + childrenArrayList.get(i).getData().getVideoUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
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

                ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit_name_prefixed: " + childrenArrayList.get(i).getData().getSubredditR() + "\n" +
                            "author: " + childrenArrayList.get(i).getData().getAuthor() + "\n" +
                            "created: " + childrenArrayList.get(i).getData().getDate() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "selftext: " + childrenArrayList.get(i).getData().getBody() + "\n" +
                            "url: " + childrenArrayList.get(i).getData().getPostUrl() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "thumbnail: " + childrenArrayList.get(i).getData().getThumbnail() + "\n" +
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
                            .addHeader("Authorization", credentials);
                    return chain.proceed(newRequest.build());
                }
            });
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RedditAPI redditAPI = retrofit.create(RedditAPI.class);
            Call<TokenResponse> call = redditAPI.getAccessToken("authorization_code",
                    code, REDIRECT_URI);
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    Log.d(TAG, "code: " + code);
                    Log.d(TAG, "onResponse: Server Response" + response.body().toString());
                    Log.d(TAG, "onResponse: Server Response" + response.body().getAccessToken());


                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ERROR: " + t.getMessage());
                }
            });

        }

    }


    public void setTokenObj(){
        TokenResponse tokenResponse;
    }
}
