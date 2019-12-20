package lilee.hd.anotterredditapp.database;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.post.Post;
import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static lilee.hd.anotterredditapp.reddit.Constants.TAG_TOKEN;

public class OtterRepository {
    private static final String TAG = "OtterRepository";
    private static OtterRepository instance;
    private RedditAPI redditAPI;
    private RedditNetworking redditNetworking;
//
//    private TokenDao tokenDao;
//    private LiveData<TokenResponse> currentToken;
//    private MediatorLiveData<TokenResponse> mObservableToken;
//
//    private SubredditDao subredditDao;
//    private LiveData<List<Subreddit>> allSubreddits;

    private ArrayList<Post> dataSet = new ArrayList<>();
    private Post post;
    private LiveData<List<Post>> mPostList;
//    private MutableLiveData<ArrayList<Post>> dataSet = new MutableLiveData<>();
//    private MutableLiveData<Post> post = new MutableLiveData<>();
//    private LiveData<List<Post>> mPostList;


    public static OtterRepository getInstance() {
        if (instance == null) {
            instance = new OtterRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Post>> getPosts() {
        final MutableLiveData<ArrayList<Post>> data = new MutableLiveData<>();
        redditNetworking = new RedditNetworking();
        redditNetworking.redditCall();
        data.setValue(dataSet);
        return data;
    }
}
