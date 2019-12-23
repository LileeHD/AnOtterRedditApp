package lilee.hd.anotterredditapp.subreddits;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import lilee.hd.anotterredditapp.database.RedditRepository;
import lilee.hd.anotterredditapp.model.post.Post;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;

public class SubredditViewModel extends ViewModel {
    private MutableLiveData<List<Subreddit>> mSubredditList;
    private MutableLiveData<Subreddit> mSubreddit;

    RedditRepository repository;

    public void init(){
        if (mSubreddit != null){
            return;
        }
    }



}
