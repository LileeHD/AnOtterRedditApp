package lilee.hd.anotterredditapp.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.viewmodel.PostViewModel;

public class DetailFragment extends Fragment {
    private PostViewModel mViewModel;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_post_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    private void initViewModel() {
//        mViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
//        mViewModel.init();
//        mViewModel.getFeedRepository().observe(this, feed -> {
//            List<Children> childrenList = feed.getData().getChildren();
//            postsList.addAll(childrenList);
//            adapter.notifyDataSetChanged();
//            Log.d(TAG, "initViewModel: ");
//        });
//
//    }
//
//    private void initPostView() {
//        if (adapter == null) {
//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
//            adapter = new PostViewAdapter(getContext(), postsList, this);
//
//            postView.addItemDecoration(dividerItemDecoration);
//            postView.setItemAnimator(new DefaultItemAnimator());
//            postView.setAdapter(adapter);
//            postView.setLayoutManager(new LinearLayoutManager(getContext()));
//            postView.setHasFixedSize(true);
//            mSwipeRefreshLayout.setOnRefreshListener(() -> {
//                adapter.notifyDataSetChanged();
//            });
//            Log.d(TAG, "initPostView: ");
//        }
//    }
}
