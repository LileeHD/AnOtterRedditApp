package lilee.hd.anotterredditapp.home_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;

public class OtterFragmentChild extends Fragment {
    private static final String TAG = "OtterFragmentChild";
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.post_rview)
    RecyclerView recyclerView;
    public OtterFragmentChild() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otter_feed, container, false);
        ButterKnife.bind(this, view);
        getOtterFeed();
        return view;

    }

    private void getOtterFeed(){
        RedditNetworking networking = new RedditNetworking();
        networking.otterCall();
    }
}
