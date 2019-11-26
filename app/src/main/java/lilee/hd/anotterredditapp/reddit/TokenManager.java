package lilee.hd.anotterredditapp.reddit;

import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import lilee.hd.anotterredditapp.MainActivity;
import lilee.hd.anotterredditapp.reddit.model.TokenResponse;
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
import static lilee.hd.anotterredditapp.reddit.Constants.TAG;
import static lilee.hd.anotterredditapp.reddit.Constants.TAG_TOKEN;

public class TokenManager {

    private void startSignIn() {

        Uri baseUri = Uri.parse(OAUTH_BASE_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID);
        builder.appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE);
        builder.appendQueryParameter(STATE_KEY, STATE);
        builder.appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI);
        builder.appendQueryParameter(DURATION_KEY, DURATION);
        builder.appendQueryParameter(SCOPE_KEY, SCOPE);

//        String url = builder.toString();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        startActivity(intent);
//        Log.d(TAG_TOKEN, "startSignIn: URL " + url);
    }


}
