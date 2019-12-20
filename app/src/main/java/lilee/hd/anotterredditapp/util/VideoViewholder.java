package lilee.hd.anotterredditapp.util;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import lilee.hd.anotterredditapp.R;

public class VideoViewholder extends RecyclerView.ViewHolder{
    private ConstraintLayout media_container;
    private ImageView thumbnail, volumeControl;
    private View parent;
    private RequestManager requestManager;

    public VideoViewholder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
//        media_container = itemView.findViewById(R.id.post_container);
        thumbnail = itemView.findViewById(R.id.post_video);
//        volumeControl = itemView.findViewById(R.id.volume_control);
    }

    public void onBind(VideoModel mediaObject, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);

        this.requestManager
                .load(mediaObject.getVideoThumbnail())
                .into(thumbnail);
    }
}
