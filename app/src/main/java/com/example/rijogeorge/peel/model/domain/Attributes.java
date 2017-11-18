
package com.example.rijogeorge.peel.model.domain;

import java.util.HashSet;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("aspect")
    private String mAspect;
    @SerializedName("countries")
    //private List<String> mCountries;
    private HashSet<String> mCountries;
    @SerializedName("language")
    private String mLanguage;

    public String getAspect() {
        return mAspect;
    }

    public void setAspect(String aspect) {
        mAspect = aspect;
    }

//    public List<String> getCountries() {
//        return mCountries;
//    }
//
//    public void setCountries(List<String> countries) {
//        mCountries = countries;
//    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public HashSet<String> getmCountries() {
        return mCountries;
    }

    public void setmCountries(HashSet<String> mCountries) {
        this.mCountries = mCountries;
    }
}