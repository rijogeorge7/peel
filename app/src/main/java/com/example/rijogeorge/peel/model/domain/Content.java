
package com.example.rijogeorge.peel.model.domain;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("content")
    private List<Content> mContent;
    @SerializedName("name")
    private String mName;
    @SerializedName("preroll")
    private List<Preroll> mPreroll;
    @SerializedName("videos")
    private List<Video> mVideos;

    public List<Content> getContent() {
        return mContent;
    }

    public void setContent(List<Content> content) {
        mContent = content;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Preroll> getPreroll() {
        return mPreroll;
    }

    public void setPreroll(List<Preroll> preroll) {
        mPreroll = preroll;
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }

}
