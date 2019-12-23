package lilee.hd.anotterredditapp.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.post.Post;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.database.RedditRepository;

public class PostViewModel extends ViewModel {

    private MutableLiveData<Feed> mutableLiveData;
    private final MutableLiveData<Post>currentPost = new MutableLiveData<Post>();

    private MutableLiveData<TokenResponse> mutableLiveDataToken;
    private final MutableLiveData<TokenResponse>currentToken = new MutableLiveData<TokenResponse>();

    private RedditRepository repository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        repository = RedditRepository.getInstance();
//        TODO here the logic between feeds
        mutableLiveData = repository.getFeed();
//        if (account != null){
//            mutableLiveData = repository.getUserFeed();
//        }else if (String userInput){
//            mutableLiveData = repository.searchResult();
//        }else{
//            mutableLiveData = repository.getFeed();
//        }
    }

    public MutableLiveData<Feed> getFeedRepository(){
        return mutableLiveData;
    }
    public MutableLiveData<TokenResponse>getTokenAccess(){
        return mutableLiveDataToken;
    }
    public void selectItem(Post post){
        currentPost.setValue(post);
    }
    
    public LiveData<Post>getCurrentPost(){
        currentPost.getValue();
        return currentPost;
    }
}
