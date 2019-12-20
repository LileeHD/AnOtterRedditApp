package lilee.hd.anotterredditapp.home_ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.adapter.PostViewAdapter;
import lilee.hd.anotterredditapp.model.post.Post;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import lilee.hd.anotterredditapp.viewmodel.PostViewModel;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_btn)
    ImageButton searchBtn;
    @BindView(R.id.home_list_view)
    RecyclerView postView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Snackbar snackbar;
    private PostViewAdapter adapter;
    private Post post;
    private ArrayList<Post> postsList = new ArrayList<>();
    private TokenResponse tokenResponse;
    private PostViewModel mPostViewModel;
    private String mSearchResult;
    private String sort = "new";

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //    top bar
        //    tabs
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        searchBtn.setOnClickListener(this);
        RedditNetworking networking = new RedditNetworking();
        networking.redditCall();
        initviewModel();
        initPostView();
        Log.d(TAG, "onCreateView: PHARAH");
        return view;
    }

    private void initviewModel() {
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.init();
        mPostViewModel.getPostList().observe(this, posts -> adapter.notifyDataSetChanged());
        Log.d(TAG, "initviewModel: ");
    }
    private void initPostView() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL);
        adapter = new PostViewAdapter(getContext(),mPostViewModel.getPostList().getValue());

        postView.addItemDecoration(dividerItemDecoration);
        postView.setItemAnimator(new DefaultItemAnimator());
        postView.setAdapter(adapter);
        postView.setLayoutManager(new LinearLayoutManager(getContext()));
        postView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.notifyDataSetChanged();
        });
        Log.d(TAG, "initPostView: ");
    }

    @Override
    public void onClick(View v) {
        String feedName = searchEditText.getText().toString();
        if (!feedName.equals("")) {
            mSearchResult = feedName;
            searchResult();
        } else {
            Log.d(TAG, "onClick: SET A SNACK BAR ");
        }
    }


    private void searchResult() {
        RedditNetworking networking = new RedditNetworking();
        networking.searchCall(mSearchResult);
    }
}
