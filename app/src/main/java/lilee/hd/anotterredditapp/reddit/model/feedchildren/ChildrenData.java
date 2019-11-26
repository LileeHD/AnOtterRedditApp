package lilee.hd.anotterredditapp.reddit.model.feedchildren;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildrenData {
    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("ups")
    @Expose
    private int ups;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("is_video")
    @Expose
    private boolean isVideo;

    @SerializedName("subscribers")
    @Expose
    private String subscribers;

    @SerializedName("public_description")
    @Expose
    private String publicDescription;

    @SerializedName("header_title")
    @Expose
    private String headerTitle;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    @Override
    public String toString() {
        return "Data{" +
                "subreddit='" + subreddit + '\'' +
                ", ups=" + ups +
                ", title='" + title + '\'' +
                ", isVideo='" + isVideo + '\'' +
                ", url='" + url + '\'' +
                ", subscribers='" + subscribers + '\'' +
                ", publicDescription='" + publicDescription + '\'' +
                ", headerTitle='" + headerTitle + '\'' +
                '}';
    }
}
