package com.example.newsbyabhay;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Article {







    @SerializedName("source")
    private SourceModel source;


    @SerializedName("author")
    private String author;


    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;

    @SerializedName("description")
    private String text;



    public SourceModel getSource() {
        return source;
    }


    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }



    public String getText() {
        return text;
    }
}
