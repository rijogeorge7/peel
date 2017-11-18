
package com.example.rijogeorge.peel.model.domain;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Preroll {

    @SerializedName("name")
    private String mName;
    @SerializedName("videos")
    private List<Video> mVideos;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }

}
