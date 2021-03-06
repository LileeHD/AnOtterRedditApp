package lilee.hd.anotterredditapp.model.post;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post_table")
public class Post {
    //    Media
    private Media media;
    @PrimaryKey(autoGenerate = true)
    private String id;
    //top bar
    @SerializedName("subreddit_name_prefixed")
    @Expose
    private String subredditR;
    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("created")
    @Expose
    private long date;
    //    body
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("selftext")
    @Expose
    private String body;
    // bottom bar
    @SerializedName("ups")
    @Expose
    private int ups;
    @SerializedName("url")
    @Expose
    private String imageUrl;
    @SerializedName("num_comments")
    @Expose
    private int numComments;

    private String videoUrl;

    @SerializedName("is_video")
    private boolean isVideo = false;

//    ----------------------------------------Getter Setter----------------------------------------


    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubredditR() {
        return subredditR;
    }

    public void setSubredditR(String subredditR) {
        this.subredditR = subredditR;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getVideoUrl() {
        if (videoUrl==null){
            RedditVideo redditVideo = media != null ? media.getRedditVideo() : null;
            videoUrl = redditVideo != null ? redditVideo.getUrl() : null;
        }
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        this.isVideo = video;
    }
}
