package com.example.newsbyabhay;

import com.google.gson.annotations.SerializedName;

public class SourceModel {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
