package lilee.hd.anotterredditapp.reddit.model.feedchildren;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {
    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private ChildrenData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ChildrenData getData() {
        return data;
    }

    public void setData(ChildrenData data) {
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
