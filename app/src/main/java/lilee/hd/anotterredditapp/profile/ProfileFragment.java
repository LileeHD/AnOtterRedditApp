package lilee.hd.anotterredditapp.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.database.OtterDatabase;
import lilee.hd.anotterredditapp.home_ui.SectionPagerAdapter;
import lilee.hd.anotterredditapp.model.account.Account;
import lilee.hd.anotterredditapp.model.token.TokenResponse;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import okhttp3.OkHttpClient;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProfileFragment";
    public OkHttpClient.Builder client;
    public RedditNetworking networking = new RedditNetworking();
    @BindView(R.id.signin_msg) Button mLoginBtn;
    @BindView(R.id.unlogged_otter) ImageView userPic;
    @BindView(R.id.login_state) ImageView loginState;
    @BindView(R.id.user_name) TextView userName;
    @BindView(R.id.subreddit_list) RecyclerView subredditView;
    private View view;
    private OtterDatabase mDatabase;
    private TokenResponse tokenResponse;
    private Account account;
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
        tokenLogic();
    }

//    private void setUpViewPager() {
//        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager(), 1);
//        adapter.addFragment(new SubredditsFragmentChild());
//
//        adapter.addFragment(new CommentsFragmentChild());
//
//        ViewPager viewPager = view.findViewById(R.id.viewpager_container);
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = view.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_feed);
//        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_info);
//    }

    private void tokenLogic() {
        final Uri uri = Objects.requireNonNull(getActivity()).getIntent().getData();
        networking = new RedditNetworking();
        networking.catchAccessToken(uri);
//        setAccount();
    }

    /**
     * TODO, don't panic, finish the bd BEFORE !!
     */
    private void setAccount(){
        if (tokenResponse != null){
//            account = mDatabase.accountDao().getCurrentAccount();
//            profileViews(account);
        }
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
