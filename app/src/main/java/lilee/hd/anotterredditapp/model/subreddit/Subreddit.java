package lilee.hd.anotterredditapp.model.subreddit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "subreddit_table")
public class Subreddit {
    @PrimaryKey
    private int id;

    private int sort;

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    private String name;

    @ColumnInfo(name = "icon_img")
    @SerializedName("icon_img")
    private String iconUrl;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @ColumnInfo(name = "subscribers_count")
    @SerializedName("subscribers_count")
    private int subscribers;

//    @Ignore

    public Subreddit(int sort, String name, String iconUrl, String description, int subscribers) {
        this.sort = sort;
        this.name = name;
        this.iconUrl = iconUrl;
        this.description = description;
        this.subscribers = subscribers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }
}
