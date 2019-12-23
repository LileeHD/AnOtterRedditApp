package lilee.hd.anotterredditapp.home_ui;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.adapter.PostViewAdapter;
import lilee.hd.anotterredditapp.detail.DetailActivity;
import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import lilee.hd.anotterredditapp.util.ConverterUtil;
import lilee.hd.anotterredditapp.viewmodel.PostViewModel;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class HomeFragment extends Fragment implements PostViewAdapter.PostClickListener {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.search_edit_text)
    AppCompatEditText searchEditText;
    @BindView(R.id.search_btn)
    ImageButton searchBtn;
    @BindView(R.id.home_list_view)
    RecyclerView postView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.connection_info)
    TextView connectionInfo;
    private Snackbar snackbar;
    private PostViewAdapter adapter;
    private Children post;
    private ArrayList<Children> postsList = new ArrayList<>();
    private TokenResponse tokenResponse;
    private PostViewModel mPostViewModel;
    private String mSearchResult;
    private boolean mIsRefreshing = false;
    private String sort = "new";

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        checkConnection();
        searchBtn.setOnClickListener(v -> {
            initPostView();
            String feedName = searchEditText.getText().toString();
            if (!feedName.equals("")){
                mSearchResult = feedName;
                mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
                mPostViewModel.init();
                mPostViewModel.getSearchResults(mSearchResult).observe(this, feed -> {
                    List<Children> childrenList = feed.getData().getChildren();
                    postsList.addAll(childrenList);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "initViewModel: ");
                });
            }else {
                Log.d(TAG, "onClick: SET A SNACK BAR ");
            }
        });
        searchEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                onSearchClick(v);
                closeKeyboard();
                return true;
            }
            return false;
        });
        Log.d(TAG, "onCreateView: PHARAH");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            String snackMsg = "Is loading...";
            mSwipeRefreshLayout.setOnRefreshListener(() -> {
                Log.d(TAG, "onRefresh: refresh the list");
                snackbar = Snackbar.make(view.findViewById(R.id.coordinator), snackMsg, Snackbar.LENGTH_SHORT);
                snackbar.show();
                mSwipeRefreshLayout.setRefreshing(false);
                updateRefreshingUI();
            });
        }
    }

    private void closeKeyboard() {
        View view = (Objects.requireNonNull(getActivity())).getCurrentFocus();
        if (view != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            view.clearFocus();
            }
        }
    }

    private void initViewModel() {
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.init();
        mPostViewModel.getFeedRepository().observe(this, feed -> {
            List<Children> childrenList = feed.getData().getChildren();
            postsList.addAll(childrenList);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "initViewModel: ");
        });

    }

    private void initPostView() {
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
            adapter = new PostViewAdapter(getContext(), postsList, this);
            postView.addItemDecoration(dividerItemDecoration);
            postView.setItemAnimator(new DefaultItemAnimator());
            postView.setAdapter(adapter);
            postView.setLayoutManager(new LinearLayoutManager(getContext()));
            postView.setHasFixedSize(true);
//            mSwipeRefreshLayout.setOnRefreshListener(this::initPostView);
            mSwipeRefreshLayout.setOnRefreshListener(() -> {
                adapter.notifyDataSetChanged();
            });
            Log.d(TAG, "initPostView: ");
        }
    }

    private void searchResult() {
        RedditNetworking networking = new RedditNetworking();
        networking.searchCall(mSearchResult);
    }

// Click
    @Override
    public void onPostClick(PostViewModel model, int position) {
        mPostViewModel = new PostViewModel();
        post = postsList.get(position);
        postView.setOnClickListener(v -> {
            mPostViewModel.getCurrentPost();
        });
        Intent intent = new Intent(getContext(), DetailActivity.class);
        startActivity(intent);
        Log.d(TAG, "onPostClick: " + mPostViewModel.getCurrentPost());
    }

    @Override
    public void onSearchClick(View view) {
        String feedName = searchEditText.getText().toString();
        if (!feedName.equals("")) {
            mSearchResult = feedName;
            searchResult();
        } else {
            Log.d(TAG, "onClick: SET A SNACK BAR ");
        }
    }

    //  Navigation
    private void updateRefreshingUI() {
        mSwipeRefreshLayout.setRefreshing(mIsRefreshing);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            mSwipeRefreshLayout.setRefreshing(true);
            initViewModel();
            initPostView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            initViewModel();
            initPostView();
        }else{
            connectionInfo.setVisibility(View.VISIBLE);
            connectionInfo.setText(getText(R.string.no_connected));
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }
}
