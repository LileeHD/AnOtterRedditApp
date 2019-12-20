package lilee.hd.anotterredditapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import lilee.hd.anotterredditapp.database.OtterRepository;
import lilee.hd.anotterredditapp.model.post.Feed;
import lilee.hd.anotterredditapp.model.post.Post;

public class PostViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>>mPostList= new MutableLiveData<>();
    private OtterRepository mRepo;

    public void init(){
        if(mPostList != null){
            return;
        }
        mRepo = OtterRepository.getInstance();
        mPostList = mRepo.getPosts();

    }

    public MutableLiveData<ArrayList<Post>> getPostList(){
        return mPostList;
    }
}
