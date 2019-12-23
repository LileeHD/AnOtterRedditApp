package lilee.hd.anotterredditapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapp.R;
import lilee.hd.anotterredditapp.model.post.Children;
import lilee.hd.anotterredditapp.viewmodel.PostViewModel;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostViewHolder>{

    private static final String TAG = "PostViewAdapter";

    private Context mContext;
    private Children post;
    private ArrayList<Children> posts;
    private PostClickListener mListener;
    private PostViewModel postViewModel;

    public PostViewAdapter(Context context, ArrayList<Children> posts, PostClickListener listener){
        this.mContext = context;
        this.posts = posts;
        this.mListener = listener;
        Log.d(TAG, "PostViewAdapter: ");
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView rootView = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        postViewModel = new PostViewModel();
        return new PostViewHolder(rootView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        post = posts.get(position);
        holder.postTitle.setText(post.getData().getTitle());
        holder.rSubredditName.setText(post.getData().getSubredditR());
        holder.postAuthor.setText(post.getData().getAuthor());
        if (post.getData().getImageUrl()==null){
            holder.postThumbnail.setVisibility(View.GONE);
        }else {
            imageLoader(holder);
        }
        if (post.getData().getBody().isEmpty()){
            holder.postBody.setVisibility(View.GONE);
        }else {
            holder.postBody.setText(post.getData().getBody());
        }


    }

    private void imageLoader(PostViewHolder holder) {
            RequestOptions defaultOptions = new RequestOptions()
                    .error(null);
            Glide.with(mContext)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(post.getData().getImageUrl())
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

    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        PostClickListener listener;

        PostViewHolder(@NonNull View itemView, PostClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onPostClick(postViewModel,getAdapterPosition());
        }
    }

    public interface PostClickListener {
        void onPostClick(PostViewModel postViewModel, int position);

        void onSearchClick(View view);
    }
}
