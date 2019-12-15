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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.reddit.RedditNetworking;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_btn)
    ImageButton searchBtn;

    //    top bar
    //    tabs
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    private View view;
    private String mSearchResult;
    private String sort = "new";

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        searchBtn.setOnClickListener(this);
        setUpViewPager();
        return view;
    }
    @Override
    public void onClick(View v) {
        String feedName = searchEditText.getText().toString();
        if (!feedName.equals("")) {
            mSearchResult = feedName;
            searchResult();
        } else {
            Log.d(TAG, "onClick: Type a word ffs!");
        }
    }
    private void setUpViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager(), 1);
        adapter.addFragment(new DefaultFragmentChild());
        adapter.addFragment(new OtterFragmentChild());

        ViewPager viewPager = view.findViewById(R.id.viewpager_container);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_mug);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_otter);

    }

    private void searchResult() {
        RedditNetworking networking = new RedditNetworking();
        networking.searchCall(mSearchResult);
    }
}
