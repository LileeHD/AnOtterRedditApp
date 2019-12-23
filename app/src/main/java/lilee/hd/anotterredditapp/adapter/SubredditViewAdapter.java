package lilee.hd.anotterredditapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.model.subreddit.Subreddit;
import lilee.hd.anotterredditapp.viewmodel.SubredditViewModel;

public class SubredditViewAdapter extends RecyclerView.Adapter<SubredditViewAdapter.SubredditViewHolder> {
    private SubredditViewModel viewModel;
    private List<Subreddit> subreddits;
    @NonNull
    @Override
    public SubredditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SubredditViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SubredditViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.subreddit_name) TextView subredditName;
        @BindView(R.id.subreddit_description) TextView subredditDescription;
        @BindView(R.id.fav_icon) ImageButton subredditFavBtn;
        @BindView(R.id.subscribers_num) TextView subscribersNum;

        public SubredditViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
