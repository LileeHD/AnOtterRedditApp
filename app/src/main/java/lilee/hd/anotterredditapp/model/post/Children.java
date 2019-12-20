package lilee.hd.anotterredditapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {
    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private Post data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Post getData() {
        return data;
    }

    public void setData(Post data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Children{" +
                "kind='" + kind + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
