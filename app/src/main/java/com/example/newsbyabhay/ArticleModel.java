package com.example.newsbyabhay;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleModel {

    /* new code  from medium.com**/

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<Article> articles = null;

    /*above code */

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
