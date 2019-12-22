package lilee.hd.anotterredditapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.post.Post;
import lilee.hd.anotterredditapp.util.RedditRepository;

public class PostViewModel extends ViewModel {

    private MutableLiveData<Feed> mutableLiveData;
    private final MutableLiveData<Post>currentPost = new MutableLiveData<Post>();
    private RedditRepository repository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        repository = RedditRepository.getInstance();
        mutableLiveData = repository.getFeed();
    }

    public MutableLiveData<Feed> getFeedRepository(){
        return mutableLiveData;
    }

    public void selectItem(Post post){
        currentPost.setValue(post);
    }
    public LiveData<Post>getCurrentPost(){
        currentPost.getValue();
        return currentPost;
    }
}
