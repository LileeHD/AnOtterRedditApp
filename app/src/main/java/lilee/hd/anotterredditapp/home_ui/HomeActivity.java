package lilee.hd.anotterredditapp.home_ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.subreddits.SaveRedditFragment;
import lilee.hd.anotterredditapp.profile.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Context mContext = HomeActivity.this;
    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;
    private ProfileFragment profileFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavSetup();

        if (savedInstanceState == null && getIntent().getData() != null){
            navView.setSelectedItemId(R.id.ic_profile);
        }else {
            navView.setSelectedItemId(R.id.ic_home);
        }
    }

    private void bottomNavSetup() {
        navView = findViewById(R.id.bottom_nav_bar);
        navListener = item -> {
            Fragment selectedFragment = new HomeFragment();
            switch (item.getItemId()) {
                case R.id.ic_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.ic_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.ic_otter:
                    selectedFragment = new SaveRedditFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        };
        navView.setOnNavigationItemSelectedListener(navListener);
    }
}
