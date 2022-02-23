package com.geektech.newsapp40.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.NewsModel.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.databinding.ItemNewsBinding;
import com.geektech.newsapp40.ui.activity.MainActivity;
import com.geektech.newsapp40.ui.fragments.NewsFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CountViewHolder> {
    Context context;
    ArrayList<NewsModel> newsList;
    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CountViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        holder.bind(newsList.get(position));
        if (position % 2 == 0) {
            holder.binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addItem(NewsModel news) {
        newsList.add(0, news);
        notifyItemInserted(newsList.lastIndexOf(news));
    }

    public void setNewsList(ArrayList<NewsModel> newsList) {
        this.newsList = newsList;
        notifyItemInserted(0);
    }

    static class CountViewHolder extends RecyclerView.ViewHolder {
        private final ItemNewsBinding binding;

        public CountViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NewsModel newsModel) {
            Date date = Calendar.getInstance().getTime();
            binding.tvTimeItem.setText(date.toString());
            binding.tvItemSave.setText(newsModel.getTitle());
        }
    }

}
