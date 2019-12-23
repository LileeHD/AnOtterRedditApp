package lilee.hd.anotterredditapp;

import android.app.Application;

import lilee.hd.anotterredditapp.database.OtterDatabase;
import lilee.hd.anotterredditapp.database.RedditRepository;

public class OtterApp extends Application {


    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public OtterDatabase getDatabase() {
        return OtterDatabase.getInstance(this, mAppExecutors);
    }

    public RedditRepository getRepository() {
        return RedditRepository.getInstance();
    }

//    public TokenRepository getTokenInfo(){
//        return TokenRepository.getInstance(getDatabase());
//    }
}
