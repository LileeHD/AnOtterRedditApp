package lilee.hd.anotterredditapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.reddit.model.Feed;
import lilee.hd.anotterredditapp.reddit.model.TokenResponse;
import lilee.hd.anotterredditapp.reddit.model.feedchildren.Children;
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

public class MainActivity extends AppCompatActivity {
    public OkHttpClient.Builder client;
    private Button btn;
    private Button login;
    private EditText mFeedName;
    private String mCurrentFeed;
    private String sort = "new";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_result);
        btn = findViewById(R.id.btnFetchFeed);
        mFeedName = findViewById(R.id.edit_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedName = mFeedName.getText().toString();
                if (!feedName.equals("")) {
                    mCurrentFeed = feedName;
                    searchCall();
                } else {
                    searchCall();
                }
            }
        });

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
    }

    private void searchCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        String time = "new";
        Call<Feed> call = redditAPI.searchSubreddit(mCurrentFeed, time, sort);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d("searchCall", "onResponse: Server Response" + response.toString());
                Log.d("searchCall", "onResponse: received information" + response.body().toString());

                ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d("searchCall", "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "subreddit: " + childrenArrayList.get(i).getData().getSubreddit() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "ups: " + childrenArrayList.get(i).getData().getUps() + "\n" +
                            "isVideo: " + childrenArrayList.get(i).getData().isVideo() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }
                if (!response.isSuccessful()) {
                    textView.setText("Code" + response.code());
                    return;
                }
                for (Children post : childrenArrayList) {
                    String content = "";
                    content += "kind: " + post.getKind() + "\n";
                    content += "subreddit: " + post.getData().getSubreddit() + "\n";
                    content += "title: " + post.getData().getTitle() + "\n";
                    content += "ups: " + post.getData().getUps() + "\n";
                    content += "isVideo: " + post.getData().isVideo() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e("searchCall", "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }
    private void redditCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<Feed> call = redditAPI.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());

                ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                for (int i = 0; i < childrenArrayList.size(); i++) {

                    Log.d(TAG_TOKEN, "onResponse: \n" +
                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
                            "title: " + childrenArrayList.get(i).getData().getTitle() + "\n" +
                            "subscribers: " + childrenArrayList.get(i).getData().getSubscribers() + "\n" +
                            "public_description: " + childrenArrayList.get(i).getData().getPublicDescription() + "\n" +
                            "header_title: " + childrenArrayList.get(i).getData().getHeaderTitle() + "\n" +
                            "url: " + childrenArrayList.get(i).getData().getUrl() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        catchAccessToken();

    }

    private void startLogin(){
        Uri baseUri = Uri.parse(OAUTH_BASE_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID);
        builder.appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE);
        builder.appendQueryParameter(STATE_KEY, STATE);
        builder.appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI);
        builder.appendQueryParameter(DURATION_KEY, DURATION);
        builder.appendQueryParameter(SCOPE_KEY, SCOPE);
        String url = builder.toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
        Log.d(TAG_TOKEN, "startLogin: URL: "+url);
    }

    private void catchAccessToken() {
        final Uri uri = getIntent().getData();
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
            Call<TokenResponse> call = redditAPI.getAccessToken("grant_type=authorization_code&",
                    "code="+code,"&redirect_uri="+REDIRECT_URI);
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    Log.d(TAG_TOKEN, "code: " + code);
                    Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());


                }
                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
                    Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
