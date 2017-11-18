package com.example.rijogeorge.peel.utilities;

import com.example.rijogeorge.peel.model.domain.Content;
import com.example.rijogeorge.peel.model.domain.PlayList;
import com.example.rijogeorge.peel.model.domain.Preroll;
import com.example.rijogeorge.peel.model.domain.Video;
import com.example.rijogeorge.peel.model.mock.ContentJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rijogeorge on 11/18/17.
 */

public class PlaylistHelper {
    private static Content content;

    //return a list of legal playlist for content and country name
    public static List<PlayList> getPlayLists(String contentName, String country) {
        content = new Gson().fromJson(ContentJson.contentJson,Content.class);
        List<PlayList> playLists=new ArrayList<>();
        List<Content> nameFilteredContents = getNameFilteredContents(contentName);
        for(Content content : nameFilteredContents) {
            List<Video> videos= applyCountryFilter(content.getVideos(),country);
            for(Video video : videos) {
                List<Video> prerollVideos = getMatchedPrerollVideos(video, content.getPreroll(), country);
                if(prerollVideos.size()>0) {
                    PlayList pl = new PlayList();
                    pl.setContentVideo(video);
                    pl.setPrerollVideos(prerollVideos);
                    playLists.add(pl);
                }
            }
        }
        return playLists;

    }

    //get all the contents for the name for example all the contents of name MI3
    private static List<Content> getNameFilteredContents(String contentName) {
        List<Content> filteredContents = new ArrayList<>();
        for(Content content : content.getContent()) {
            if(content.getName().equals(contentName)) {
                filteredContents.add(content);
            }
        }
        return filteredContents;
    }

    // accept a list of videos and country name and return all videos filtered with that country
    private static List<Video> applyCountryFilter(List<Video> videos, String country) {
        List<Video> filteredVideo=new ArrayList<>();
        for(Video video : videos) {
            if(video.getAttributes().getmCountries().contains(country)) {
                filteredVideo.add(video);
            }
        }
        return filteredVideo;
    }

    //return a list of preroll videos filtered with preroll name and country
    private static List<Video> getMatchedPrerollVideos(Video video, List<Preroll> prerolls, String country) {
        List<Preroll> nameFilteredPrerolls = getPrerollsForName(prerolls);
        List<Video> videos=new ArrayList<>();
        for(Preroll preroll : nameFilteredPrerolls) {
            for(Video prerollVideo : preroll.getVideos()) {
                if(prerollVideo.getAttributes().getmCountries().contains(country) && prerollVideo.getAttributes().getAspect().equals(video.getAttributes().getAspect()) && prerollVideo.getAttributes().getLanguage().equals(video.getAttributes().getLanguage())) {
                    videos.add(prerollVideo);
                }
            }
        }
        return videos;
    }

    // Accept a list of prerolls and return a list of prerolls filtered with preroll name
    private static List<Preroll> getPrerollsForName(List<Preroll> prerolls) {
        List<Preroll> prerollsFilteredForName = new ArrayList<>();
        for(Preroll contentPreroll : content.getPreroll()) {
            for(Preroll preroll : prerolls){
                if(preroll.getName().equals(contentPreroll.getName()))
                    prerollsFilteredForName.add(contentPreroll);
            }
        }
        return prerollsFilteredForName;
    }
}
