package com.geektech.newsapp40.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.R;
import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.databinding.ItemNewsBinding;
import com.geektech.newsapp40.utils.interfaces.OnClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CountViewHolder> {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    static ArrayList<NewsModel> newsList = new ArrayList<>();
    static OnClick onClickInterfaces;

    public NewsAdapter(OnClick onClickInterfaces) {
        this.onClickInterfaces = onClickInterfaces;
    }


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

//    public void addItem(NewsModel news) {
//        newsList.add(0, news);
//        notifyItemInserted(0);
//
//    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(List<NewsModel> newsModelList) {
        newsList = (ArrayList<NewsModel>) newsModelList;
        notifyDataSetChanged();
    }


//    public void setNewsList(ArrayList<NewsModel> newsList) {
//        this.newsList = newsList;
//        notifyDataSetChanged();
//    }

    static class CountViewHolder extends RecyclerView.ViewHolder {
        private final ItemNewsBinding binding;

        public CountViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(NewsModel newsModel) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.ROOT);
            String date = String.valueOf(simpleDateFormat.format(newsModel.getCreatedAt()));
            binding.tvTimeItem.setText(date);
            binding.tvItemSave.setText(newsModel.getTitle());
            if (getAbsoluteAdapterPosition() % 2 == 0) {
                binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            } else {
                binding.itemNewsItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray));
            }
            itemView.setOnLongClickListener(view -> {
                onClickInterfaces.onClick(newsModel);
                return true;
            });
        }
    }

}
