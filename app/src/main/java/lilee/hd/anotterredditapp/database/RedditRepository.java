package lilee.hd.anotterredditapp.database;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;
import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.reddit.RedditService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static lilee.hd.anotterredditapp.reddit.Constants.DATABASE_REF;

public class RedditRepository {
    private static final String TAG = "RedditRepository";
    private static RedditRepository redditRepository;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference(DATABASE_REF);

    private RedditAPI redditAPI;

    public RedditRepository() {
        redditAPI = RedditService.createService(RedditAPI.class);
    }

    public static RedditRepository getInstance() {
        if (redditRepository == null) {
            redditRepository = new RedditRepository();
        }
        return redditRepository;
    }

    public MutableLiveData<Feed> getFeed() {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.getHomeFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
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

    public MutableLiveData<Feed> searchResult(String input) {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        redditAPI.inputResult(input).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
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

    public void writeNewSubreddit(String subname, String imgUrl, String description){
        Subreddit subreddit = new Subreddit(subname, imgUrl, description);
    }
    public MutableLiveData<Feed> savedSubredditsFeed() {
        MutableLiveData<Feed> feedMutableLiveData = new MutableLiveData<>();
        String list = "concatanation from db";
        redditAPI.getSavedFeed(list).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
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


}
