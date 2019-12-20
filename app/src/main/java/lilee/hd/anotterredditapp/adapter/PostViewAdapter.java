package lilee.hd.anotterredditapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.model.post.Post;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostViewHolder> {

    private static final int VIEW_TYPE_POST = 0;
    private static final int VIEW_TYPE_COMMENT = 1;
    private static final String TAG = "PostViewAdapter";

    private Context mContext;
    private Post post;
    private ArrayList<Post> posts;

    public PostViewAdapter(Context context, ArrayList<Post> posts) {
        this.mContext = context;
        this.posts = posts;
        Log.d(TAG, "PostViewAdapter: ");
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_item, parent, false);
        return new PostViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        post = posts.get(position);
        holder.postTitle.setText(post.getTitle());
        holder.rSubredditName.setText(post.getSubredditR());
        holder.postAuthor.setText(post.getAuthor());
//        holder.dateUpdate.setText(post.getDate());
        holder.postBody.setText(post.getBody());
        holder.postVotes.setText(post.getUps());
        holder.postCommentsNum.setText(post.getNumComments());
        imageLoader(holder);
    }

    public void imageLoader(PostViewHolder holder) {
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_broken_image);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(post.getThumbnail())
                .into(holder.postThumbnail);
    }

    @Override
    public int getItemCount() {
        if (posts != null && posts.size() > 0) {
            return posts.size();
        } else {
            return 0;
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_cardview)
        MaterialCardView cardView;
        //        top bar
        @BindView(R.id.post_subreddit)
        TextView rSubredditName;
        @BindView(R.id.post_author)
        TextView postAuthor;
        @BindView(R.id.date_updated)
        TextView dateUpdate;
        //        body
        @BindView(R.id.post_thumbnail)
        ImageView postThumbnail;
        @BindView(R.id.post_video)
        PlayerView postVideo;
        @BindView(R.id.post_title_view)
        TextView postTitle;
        @BindView(R.id.post_text)
        TextView postBody;
        //        bottom bar
        @BindView(R.id.post_vote_num)
        TextView postVotes;
        @BindView(R.id.commentsNum)
        TextView postCommentsNum;
        ConstraintLayout parentLayout;
//        TextView postTitle;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            postTitle = itemView.findViewById(R.id.post_title_view);
        }

//        private void bindPost() {
//            rSubredditName = itemView.findViewById(R.id.post_subreddit);
//            postAuthor = itemView.findViewById(R.id.post_author);
//            dateUpdate = itemView.findViewById(R.id.date_updated);
//            postThumbnail = itemView.findViewById(R.id.post_thumbnail);
//            postVideo = itemView.findViewById(R.id.post_video);
//            postTitle = itemView.findViewById(R.id.post_title);
//            postBody = itemView.findViewById(R.id.post_text);
//            postVotes = itemView.findViewById(R.id.post_vote_num);
//        }

    }
}
