package lilee.hd.anotterredditapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedData {
    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("dist")
    @Expose
    private String dist;

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }
}
