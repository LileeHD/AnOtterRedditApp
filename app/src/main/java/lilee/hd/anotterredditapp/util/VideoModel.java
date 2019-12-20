package lilee.hd.anotterredditapp.util;

public class VideoModel {
//    private PlayerView playerView;
//    private Uri videoUri;
//    private SimpleExoPlayer player;
//    ExoPlayer.Builder();
//    DataSource.Factory factory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"anotterredditapp"));
//
//    private boolean wasPlaying;
//    private boolean isDownloading = false;
//    private float totalLengthY = 0.0f;
//    private float touchY = -1.0f;
    private String videoUrl;
    private String videoThumbnail;

    public VideoModel(String videoUrl, String videoThumbnail) {
        this.videoUrl = videoUrl;
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
}
