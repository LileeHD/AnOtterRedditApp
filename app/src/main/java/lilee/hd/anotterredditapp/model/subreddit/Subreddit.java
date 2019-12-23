package lilee.hd.anotterredditapp.model.subreddit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "subreddit_table")
public class Subreddit {
    @PrimaryKey
    private int id;

    @SerializedName("display_name")
    private String name;

    @SerializedName("icon_img")
    private String iconUrl;

    @SerializedName("description")
    private String description;

//    @Ignore

    public Subreddit() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Subreddit(String name, String iconUrl, String description) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.description = description;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
