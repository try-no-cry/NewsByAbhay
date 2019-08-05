package com.example.newsbyabhay;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
private ArrayList<News_Single> list;
private Context context;

onItemClick varItemClicked;
public interface onItemClick{
    void onItemClicked(int index);
    }

    public NewsAdapter(ArrayList<News_Single> list, Context context) {
        this.list = list;
        this.context = context;
        varItemClicked=(onItemClick)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView tvDate,tvTitle,tvDetail,tvDescription;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);


            tvDate=itemView.findViewById(R.id.tvDate);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDetail=itemView.findViewById(R.id.tvDetail);
            tvDescription=itemView.findViewById(R.id.tvDescription);

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    varItemClicked.onItemClicked((int)itemView.getTag());
                }
            });


        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.news_single,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

    holder.itemView.setTag((Object) position);
        String date=list.get(position).getDate();
        String title=list.get(position).getTitle();
        String detail=list.get(position).getDetail();
        String description=list.get(position).getDescription();

        holder.tvDate.setText(date);
        holder.tvTitle.setText(title);
        holder.tvDetail.setText(detail);
        holder.tvDescription.setText(description);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
