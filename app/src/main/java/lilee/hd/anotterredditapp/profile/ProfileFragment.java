package lilee.hd.anotterredditapp.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.database.OtterDatabase;
import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import lilee.hd.anotterredditapp.viewmodel.PostViewModel;
import okhttp3.OkHttpClient;

import static lilee.hd.anotterredditapp.reddit.Constants.ACCESS_TOKEN_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.EXPIRE_IN_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.PACKAGE;
import static lilee.hd.anotterredditapp.reddit.Constants.REFRESH_TOKEN_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.TOKEN_TYPE;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProfileFragment";
    public OkHttpClient.Builder client;
    public RedditNetworking networking = new RedditNetworking();
    @BindView(R.id.signin_msg)
    Button mLoginBtn;
    @BindView(R.id.unlogged_otter)
    ImageView userPic;
    @BindView(R.id.login_state)
    ImageView loginState;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.subreddit_list)
    RecyclerView subredditView;
    private View view;
    private OtterDatabase mDatabase;
    private TokenResponse tokenResponse;
    private Account account;
    private PostViewModel viewModel;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        mLoginBtn.setOnClickListener(this);
//        setUpViewPager();
        return view;
    }

    private void profileViewModel() {
        viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        viewModel.init();
        viewModel.getTokenAccess().observe(this, tokenResponse -> {
            String accessToken = tokenResponse.getAccessToken();
            Log.d(TAG, "initViewModel: ");
        });

    }


    @Override
    public void onClick(View v) {
        String url = networking.setUrlRequest();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
        Log.d("SIGMA", "startLogin: URL: " + url);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        tokenLogic(tokenResponse);
        setAccount(account);
    }

    private void tokenLogic(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
        final Uri uri = Objects.requireNonNull(getActivity()).getIntent().getData();
        networking = new RedditNetworking();
        networking.catchAccessToken(uri);

    }

    /**
     * TODO, don't panic, finish the bd BEFORE !!
     */
    private void setAccount(Account account) {

    }

    public void profileViews(Account account) {
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_broken_image);
        Glide.with(this)
                .setDefaultRequestOptions(defaultOptions)
                .load(account.getProfileImageUrl())
                .into(userPic);
        mLoginBtn.setVisibility(View.GONE);
        userName.setText(account.getUsername());
        userName.setVisibility(View.VISIBLE);
    }

}
