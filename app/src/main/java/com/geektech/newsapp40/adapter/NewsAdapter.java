package com.geektech.newsapp40.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.databinding.ItemNewsBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CountViewHolder> {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    ArrayList<NewsModel> newsList = new ArrayList<>();

    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CountViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        holder.bind(newsList.get(position));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addItem(NewsModel news) {
        newsList.add(0, news);
        notifyItemInserted(0);

    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(List<NewsModel> newsModelList) {
        newsList = (ArrayList<NewsModel>) newsModelList;
        notifyDataSetChanged();
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
            if (getAdapterPosition() % 2 == 0) {
                binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            } else {
                binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray));
            }
        }
    }

}
