package com.example.newsbyabhay;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonNewApi {
   @GET("top-headlines")
    Call<ArticleModel> getArticles(@QueryMap Map<String,String> parameters);


}
