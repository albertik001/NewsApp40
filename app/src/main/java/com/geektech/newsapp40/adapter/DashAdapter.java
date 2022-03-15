package com.geektech.newsapp40.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.databinding.ItemNewsBinding;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.ViewHolderDash> {

    @NonNull
    @Override
    public ViewHolderDash onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderDash(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDash holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolderDash extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;
        public ViewHolderDash(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {

        }
    }
}
