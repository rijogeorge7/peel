package com.example.rijogeorge.peel.model.domain;

import java.util.List;

/**
 * Created by rijogeorge on 11/18/17.
 */

public class PlayList {
    List<Video> prerollVideos;
    Video contentVideo;

    public List<Video> getPrerollVideos() {
        return prerollVideos;
    }

    public void setPrerollVideos(List<Video> prerollVideos) {
        this.prerollVideos = prerollVideos;
    }

    public Video getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(Video contentVideo) {
        this.contentVideo = contentVideo;
    }
}
