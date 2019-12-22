package lilee.hd.anotterredditapp.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lilee.hd.anotterredditapp.R;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, DetailFragment.newInstance()).commit();
        }

    }
}
