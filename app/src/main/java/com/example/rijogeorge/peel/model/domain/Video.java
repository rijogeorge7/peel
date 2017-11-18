
package com.example.rijogeorge.peel.model.domain;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("attributes")
    private Attributes mAttributes;
    @SerializedName("name")
    private String mName;

    public Attributes getAttributes() {
        return mAttributes;
    }

    public void setAttributes(Attributes attributes) {
        mAttributes = attributes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
