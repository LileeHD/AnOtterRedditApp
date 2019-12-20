package lilee.hd.anotterredditapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lilee.hd.anotterredditapp.model.post.FeedData;

public class Feed {
    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private FeedData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public FeedData getData() {
        return data;
    }

    public void setData(FeedData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}
