package lilee.hd.anotterredditapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditVideo {

    @SerializedName("fallback_url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
