//package lilee.hd.anotterredditapp.model.user;
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.PrimaryKey;
//
//public class UserInfo {
//
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "name")
//    private String name;
//    @ColumnInfo(name = "icon")
//    private String iconUrl;
//    @ColumnInfo(name = "banner")
//    private String banner;
//
//    public UserInfo(@NonNull String name, String iconUrl, String banner) {
//        this.name = name;
//        this.iconUrl = iconUrl;
//        this.banner = banner;
//    }
//
//    @NonNull
//    public String getName() {
//        return name;
//    }
//
//    public void setName(@NonNull String name) {
//        this.name = name;
//    }
//
//    public String getIconUrl() {
//        return iconUrl;
//    }
//
//    public void setIconUrl(String iconUrl) {
//        this.iconUrl = iconUrl;
//    }
//
//    public String getBanner() {
//        return banner;
//    }
//
//    public void setBanner(String banner) {
//        this.banner = banner;
//    }
//}
