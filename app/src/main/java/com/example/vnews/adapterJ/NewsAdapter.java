package com.example.vnews.adapterJ;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vnews.Activity.ReadActivity;
import com.example.vnews.R;
import com.example.vnews.modelJ.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemHolder>{
    Context context;
    ArrayList<News> arrayNews;

    public NewsAdapter(Context context, ArrayList<News> arrayNews) {
        this.context = context;
        this.arrayNews = arrayNews;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_news, null);
        NewsAdapter.ItemHolder itemHolder = new NewsAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        News news = arrayNews.get(position);
        holder.Title.setText(news.getTitle());
        holder.Nguon.setText(news.getNguon());
        holder.Loai.setText(news.getLoai());
        holder.NgayDang.setText(news.getNgayDang());
        Picasso.with(context).load(news.getAnh()).into(holder.AnhMH);
    }

    @Override
    public int getItemCount() {
        return arrayNews.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView AnhMH;
        public TextView Title;
        public TextView Nguon;
        public TextView Loai;
        public TextView NgayDang;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            AnhMH = itemView.findViewById(R.id.ivAnhMH);
            Title = itemView.findViewById(R.id.tvTitle);
            Nguon = itemView.findViewById(R.id.tvNguon);
            Loai = itemView.findViewById(R.id.tvLoaiTin);
            NgayDang = itemView.findViewById(R.id.tvNgayDang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReadActivity.class);
                    intent.putExtra("idTin", String.valueOf(arrayNews.get(getPosition()).getID()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
