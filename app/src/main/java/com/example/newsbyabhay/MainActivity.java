package com.example.newsbyabhay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsAdapter.onItemClick{
private TextView tvNews;
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private RecyclerView.LayoutManager layoutManager;
private   ArrayList<News_Single> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.myRecyclerViewD);


       // tvNews=findViewById(R.id.tvNews);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonNewApi jsonNewApi=retrofit.create(JsonNewApi.class);

        Map<String,String> parameters=new HashMap<>();

        //parameters.put("sources","google-news");
        parameters.put("country","in");
        parameters.put("apiKey","c1d38449ae0d4952b820aa15bc25b7ac");

        final Call<ArticleModel> call=jsonNewApi.getArticles(parameters);

        call.enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(!response.isSuccessful()){
                   // tvNews.setText("Code "+ response.code());
                    AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Error in fetching");
                    dialog.setCancelable(true);
                    dialog.setMessage("Code "+ response.code());
                    dialog.create();

                    dialog.show();
                    return;

                }



                List<Article> articles=response.body().getArticles();

                list=new ArrayList<>();

                for(Article article:articles){
                    String data="";
                    if(article.getSource().getId()!=null)
                        data +="Source ID: "+ article.getSource().getId()+"\n";


                    if(article.getSource().getName()!=null)
                        data +="Source Name: "+ article.getSource().getName()+"\n";

                    if(article.getAuthor()!=null)
                        data +="Author: "+ article.getAuthor()+"\n";


                    data +="Title: "+ article.getTitle()+"\n";
                    data +="Url: "+ article.getUrl()+"\n";
                    data +="UrlToImage: "+ article.getUrlToImage()+"\n";
                    data +="Published at: "+ article.getPublishedAt()+"\n";
                    data +="Description: "+ article.getText()+"\n";
                    data +="Content: "+ article.getContent()+"\n";

                    data +="\n\n";

                    News_Single news=new News_Single();

                    if(article.getPublishedAt()!=null)
                        news.setDate("Published at: "+ article.getPublishedAt());
                    else news.setDate("Date Not Available");

                    if(article.getTitle()!=null)
                         news.setTitle(article.getTitle());
                    else news.setTitle("");

                    if(article.getText()!=null)
                         news.setDescription( article.getText());
                    else news.setDescription("");

                    if(article.getContent()!=null)
                         news.setDetail("   "+ article.getContent());
                    else news.setDetail("");

                    if(article.getUrl()!=null)
                        news.setLink(article.getUrl());
                    else news.setLink("");

                    if(article.getUrlToImage()!=null)
                        news.setImageLink(article.getUrlToImage());
                    //making list of displaying items
                    list.add(news);

                    //tvNews.append(data);
                }

                NewsAdapter myadapter=new NewsAdapter(list,MainActivity.this);
                layoutManager=new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myadapter);


            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                    //tvNews.setText(t.getMessage());
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Error in fetching");
                dialog.setCancelable(true);
                dialog.setMessage(t.getMessage());
                dialog.create();

                dialog.show();

            }
        });


    }


    @Override
    public void onItemClicked(int index) {
        if(list.size()!=0 && list!=null) {
            Toast.makeText(getApplicationContext(), list.get(index).getLink(), Toast.LENGTH_LONG).show();

            Intent intent=new Intent(this,WebPage.class);
            intent.putExtra("url",list.get(index).getLink());
            startActivity(intent);
        }
    }
}
