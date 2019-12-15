package lilee.hd.anotterredditapp.reddit.model.post;

import com.google.gson.annotations.SerializedName;

public class Subreddit {

    @SerializedName("display_name")
    private String name;

    @SerializedName("icon_img")
    private String iconUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
