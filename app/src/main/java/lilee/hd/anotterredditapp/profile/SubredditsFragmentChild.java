package lilee.hd.anotterredditapp.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lilee.hd.anotterredditapp.R;

public class SubredditsFragmentChild extends Fragment {
    public SubredditsFragmentChild() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_subscriptions, container, false);
        return view;
    }
}
