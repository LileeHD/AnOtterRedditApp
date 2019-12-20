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
    public void redditCall() {
        final MutableLiveData<Feed> stationList = new MutableLiveData<>();
        Call<Feed> call = redditAPI.getHomeFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());
                if (response.isSuccessful()){
                    response.body().getData().getChildren().toString();
                    ArrayList<Children> childrenArrayList = response.body().getData().getChildren();
                    for (int i = 0; i < childrenArrayList.size(); i++) {
//                    Post info = childrenArrayList.get(i).getData();
//                    String title = info.getTitle();
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
                        Log.d(TAG, "onResponse: redditCall");
                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
//                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
