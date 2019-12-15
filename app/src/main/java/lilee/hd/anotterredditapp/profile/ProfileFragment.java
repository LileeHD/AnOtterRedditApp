package lilee.hd.anotterredditapp.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.home_ui.SectionPagerAdapter;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;
import okhttp3.OkHttpClient;

import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID;
import static lilee.hd.anotterredditapp.reddit.Constants.CLIENT_ID_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION;
import static lilee.hd.anotterredditapp.reddit.Constants.DURATION_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.OAUTH_BASE_URL;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI;
import static lilee.hd.anotterredditapp.reddit.Constants.REDIRECT_URI_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE;
import static lilee.hd.anotterredditapp.reddit.Constants.RESPONSE_TYPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE;
import static lilee.hd.anotterredditapp.reddit.Constants.SCOPE_KEY;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE;
import static lilee.hd.anotterredditapp.reddit.Constants.STATE_KEY;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProfileFragment";
    public OkHttpClient.Builder client;
    @BindView(R.id.signin_msg)
    Button mLoginBtn;
    private View view;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        mLoginBtn.setOnClickListener(this);
        setUpViewPager();
        return view;
    }

    @Override
    public void onClick(View v) {
        startLogin();
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

    private void setUpViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager(), 1);
        adapter.addFragment(new SubredditsFragmentChild());

        adapter.addFragment(new CommentsFragmentChild());

        ViewPager viewPager = view.findViewById(R.id.viewpager_container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_feed);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_info);
    }

    private void startLogin() {
        Uri baseUri = Uri.parse(OAUTH_BASE_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID);
        builder.appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE);
        builder.appendQueryParameter(STATE_KEY, STATE);
        builder.appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI);
        builder.appendQueryParameter(DURATION_KEY, DURATION);
        builder.appendQueryParameter(SCOPE_KEY, SCOPE);
        String url = builder.toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
        Log.d("SIGMA", "startLogin: URL: " + url);
    }

    private void tokenLogic() {
        final Uri uri = Objects.requireNonNull(getActivity()).getIntent().getData();
        RedditNetworking networking = new RedditNetworking();
        networking.catchAccessToken(uri);
    }
}
