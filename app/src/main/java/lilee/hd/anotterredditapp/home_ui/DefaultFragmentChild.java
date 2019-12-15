package lilee.hd.anotterredditapp.home_ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.adapter.PostViewAdapter;
import lilee.hd.anotterredditapp.reddit.RedditAPI;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import lilee.hd.anotterredditapp.reddit.model.Feed;
import lilee.hd.anotterredditapp.reddit.model.post.Children;
import lilee.hd.anotterredditapp.reddit.model.post.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static lilee.hd.anotterredditapp.reddit.Constants.BASE_URL;

/**
 * Default reddit feed or
 * if user is logged, user feed
 */
public class DefaultFragmentChild extends Fragment {
    private static final String TAG = "DefaultFragmentChild";
    private RecyclerView mainRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.LayoutManager manager;
    private PostViewAdapter adapter;
    private Post post;
    private List<Post> postsList;


    public DefaultFragmentChild() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default_feed, container, false);
        setMainRecyclerView(view);
        defaultFeed();
        return view;
    }

    /**
     * TODO REDDIT API!
     */

    private void setMainRecyclerView(View view) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mainRecyclerView = view.findViewById(R.id.post_rview);
        mainRecyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        postsList = new ArrayList<>();
        mainRecyclerView.setHasFixedSize(true);
        adapter = new PostViewAdapter(getContext(), postsList);
//        adapter.setPosts(postsList);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

        private void defaultFeed(){
            RedditNetworking networking = new RedditNetworking();
            networking.redditCall();
    }
}
