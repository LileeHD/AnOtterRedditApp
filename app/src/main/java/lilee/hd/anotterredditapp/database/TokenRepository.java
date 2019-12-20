package lilee.hd.anotterredditapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;

public class TokenRepository {

    private RedditAPI redditAPI;

    private TokenDao tokenDao;
    private SubredditDao subredditDao;
    private LiveData<TokenResponse> currentToken;
    private LiveData<List<Subreddit>> allSubreddits;

    public TokenRepository(Application application) {
        OtterDatabase database = OtterDatabase.getInstance(application);
//        tokenDao = database.tokenDao();
//        currentToken = tokenDao.getcurrentToken();
    }

    public void insert(TokenResponse tokenResponse){

    }
    public void update(TokenResponse tokenResponse){

    }
    public void delete(TokenResponse tokenResponse){

    }

    public LiveData<TokenResponse> getCurrentToken(){
        return currentToken;
    }


}
