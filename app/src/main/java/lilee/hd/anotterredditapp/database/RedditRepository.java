package lilee.hd.anotterredditapp.database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.reddit.RedditService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static lilee.hd.anotterredditapp.reddit.Constants.ACCESS_TOKEN_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.AUTHORIZATION_CODE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.CODE;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI;

public class RedditRepository {
    private static final String TAG = "RedditRepository";
    private static RedditRepository redditRepository;
    public static RedditRepository getInstance(){
        if(redditRepository == null){
            redditRepository = new RedditRepository();
        }
        return redditRepository;
    }
    private RedditAPI redditAPI;

    public RedditRepository(){
        redditAPI = RedditService.createService(RedditAPI.class);
    }

    public MutableLiveData<Feed> getFeed(){
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.getHomeFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()){
                    feedMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                    feedMutableLiveData.setValue(null);
//                    TODO THe app crash when no wifi connection
            }
        });
        return feedMutableLiveData;
    }

    public MutableLiveData<Feed> searchResult(){
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        String userInput="";
        String time="";
        redditAPI.searchPost(userInput, time).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()){
                    feedMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
//                    TODO THe app crash when no wifi connection
            }
        });
        return feedMutableLiveData;
    }


    public MutableLiveData<TokenResponse> getTokenResponse(){
        MutableLiveData<TokenResponse> tokenMutableLiveData = new MutableLiveData<>();
        redditAPI.getAccessToken(AUTHORIZATION_CODE_KEY, CODE, REDIRECT_URI).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    tokenMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                tokenMutableLiveData.setValue(null);
            }
        });
        return tokenMutableLiveData;
    }

    public MutableLiveData<Feed> accountFeed(){
//        subredditlist = account.getsubreddit;
        String subredditlist="";
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.getAccountFeed(subredditlist).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()){
                    feedMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                feedMutableLiveData.setValue(null);
//                    TODO THe app crash when no wifi connection
            }
        });
        return feedMutableLiveData;
    }

    public MutableLiveData<Account> accountinfo(){
//        subredditlist = account.getsubreddit;
        String subredditlist="";
        MutableLiveData<Account> infoMutableLiveData = new MutableLiveData<>();
        redditAPI.getMyInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                infoMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
//                infoMutableLiveData.setValue(response.body());
            }
        });
        return infoMutableLiveData;
    }

}
