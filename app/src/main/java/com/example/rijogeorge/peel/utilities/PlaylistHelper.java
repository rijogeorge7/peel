package com.example.rijogeorge.peel.utilities;

import com.example.rijogeorge.peel.model.domain.Attributes;
import com.example.rijogeorge.peel.model.domain.Content;
import com.example.rijogeorge.peel.model.domain.PlayList;
import com.example.rijogeorge.peel.model.domain.Preroll;
import com.example.rijogeorge.peel.model.domain.Video;
import com.example.rijogeorge.peel.model.mock.ContentJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rijogeorge on 11/18/17.
 */

public class PlaylistHelper {
    private static Content content = null;

    //return a list of legal playlist for content and country name
    public static List<PlayList> getPlayLists(String contentName, String country) {
        try {
            content = getContentFromJson(ContentJson.contentJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

        private static Content getContentFromJson(String contentJson) throws JSONException {
            Content contentObject = new Content();
            List<Content> contentList=new ArrayList<>();
            JSONObject reader = new JSONObject(contentJson);
            JSONArray contentArray = reader.getJSONArray("content");
            for(int i=0; i< contentArray.length(); i++) {
                Content contentObj = new Content();
                JSONObject c = contentArray.getJSONObject(i);
                contentObj.setName(c.getString("name"));
                List<Preroll> preroll_list = getPrerollfromJsonArray(c.getJSONArray("preroll"));
                contentObj.setPreroll(preroll_list);
                List<Video> video = getVideoFromJsonArray(c.getJSONArray("videos"));
                contentObj.setVideos(video);
                contentList.add(contentObj);
            }
            contentObject.setContent(contentList);
            JSONArray prerollArray = reader.getJSONArray("preroll");
            List<Preroll> preroll_list = getPrerollfromJsonArray(prerollArray);
            contentObject.setPreroll(preroll_list);


            return contentObject;
        }


    private static List<Preroll> getPrerollfromJsonArray(JSONArray prerollArray) throws JSONException {
        List<Preroll> preroll_list=new ArrayList<>();
        for(int i=0; i<prerollArray.length();i++) {
            JSONObject prerollObj = prerollArray.getJSONObject(i);
            Preroll preroll=new Preroll();
            preroll.setName(prerollObj.getString("name"));

            List<Video> videos = null;
            if(prerollObj.has("videos"))
            videos = getVideoFromJsonArray(prerollObj.getJSONArray("videos"));
            preroll.setVideos(videos);
            preroll_list.add(preroll);
        }
        return preroll_list;
    }

    private static List<Video> getVideoFromJsonArray(JSONArray videosArray) throws JSONException {
        List<Video> videoList = new ArrayList<>();
        for(int i=0; i<videosArray.length();i++) {
            JSONObject videoObj = videosArray.getJSONObject(i);
            Video video = new Video();
            video.setName(videoObj.getString("name"));
            Attributes attributes=getAttributesFromJsonObject(videoObj.getJSONObject("attributes"));
            video.setAttributes(attributes);
            videoList.add(video);
        }
        return videoList;
    }

    private static Attributes getAttributesFromJsonObject(JSONObject attributesObj) throws JSONException {
        Attributes attributes = new Attributes();
        HashSet<String> countries = getCountriesHashSet(attributesObj.getJSONArray("countries"));
        attributes.setmCountries(countries);
        attributes.setLanguage(attributesObj.getString("language"));
        attributes.setAspect(attributesObj.getString("aspect"));
        return attributes;
    }

    private static HashSet<String> getCountriesHashSet(JSONArray countriesArray) throws JSONException {
        HashSet<String> countrySet = new HashSet<>();
        for(int i=0; i<countriesArray.length(); i++) {
            countrySet.add(countriesArray.getString(i));
        }
        return countrySet;
    }

}
