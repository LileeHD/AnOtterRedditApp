package lilee.hd.anotterredditapp.model.account;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @SerializedName("display_name_prefixed")
    @ColumnInfo(name = "username")
    private String username;

    @SerializedName("icon_img")
    @ColumnInfo(name = "profile_image_url")
    private String profileImageUrl;

    @SerializedName("banner_img")
    @ColumnInfo(name = "banner_image_url")
    private String bannerImageUrl;

    @ColumnInfo(name = "is_current_user")
    private boolean isCurrentUser;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    public Account(@NonNull String username, String profileImageUrl, String bannerImageUrl, boolean isCurrentUser, String url) {
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.bannerImageUrl = bannerImageUrl;
        this.isCurrentUser = isCurrentUser;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        isCurrentUser = currentUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
