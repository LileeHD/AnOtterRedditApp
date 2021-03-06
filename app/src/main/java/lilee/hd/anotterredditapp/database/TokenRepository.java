//package lilee.hd.anotterredditapp.database;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.List;
//import java.util.concurrent.Executor;
//
//import lilee.hd.anotterredditapp.reddit.RedditAPI;
//import lilee.hd.anotterredditapp.model.token.TokenResponse;
//import lilee.hd.anotterredditapp.model.subreddit.Subreddit;
//
//public class TokenRepository {
//
//    private RedditAPI redditAPI;
//
//    private TokenDao tokenDao;
//    private LiveData<TokenResponse> currentToken;
//    private LiveData<TokenResponse> refreshToken;
//
//    private static TokenRepository sInstance;
//    private Executor executor;
//    private OtterDatabase mDatabase;
//
//    public TokenRepository(final OtterDatabase database) {
//        mDatabase = database;
//        tokenDao = database.tokenDao();
//        currentToken = tokenDao.getcurrentToken();
//        refreshToken = tokenDao.getrefreshToken();
//    }
//
//    public void insert(TokenResponse tokenResponse){
//    }
//    public void update(TokenResponse tokenResponse){
//    }
//    public void delete(TokenResponse tokenResponse){
//    }
//
//    public LiveData<TokenResponse> getCurrentToken(){
//        return currentToken;
//    }
//
//
//}
