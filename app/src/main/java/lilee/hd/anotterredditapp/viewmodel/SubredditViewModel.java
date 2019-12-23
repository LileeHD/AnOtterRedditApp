package lilee.hd.anotterredditapp.viewmodel;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lilee.hd.anotterredditapp.database.RedditRepository;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;

public class SubredditViewModel extends ViewModel {

    private RedditRepository repository;
    private MutableLiveData<List<Subreddit>> mSubreddit;


}
