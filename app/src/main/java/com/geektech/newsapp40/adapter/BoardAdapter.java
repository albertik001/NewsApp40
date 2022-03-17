package com.geektech.newsapp40.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.data.local.ClientModel;
import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.databinding.ItemBoardBinding;
import com.geektech.newsapp40.utils.interfaces.OnClick;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private final OnClick onClick;
    static NewsModel newsModel;


    public BoardAdapter(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public BoardAdapter.BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.BoardViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ClientModel.titles.length;
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        private final ItemBoardBinding binding;

        public BoardViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.textThemeBoard.setText(ClientModel.titles[position]);
            binding.textBoardDescription.setText(ClientModel.titleDescription[position]);
            binding.imageBoard.setImageResource(ClientModel.imageList[position]);
            visibles(position);
        }

        private void visibles(int position) {
            if (position == getItemCount() - 1) {
                binding.btnGetStart.setVisibility(View.VISIBLE);
                binding.btnGetStart.setOnClickListener(view -> {
                    onClick.onClick(newsModel);
                });
            } else {
                binding.btnGetStart.setVisibility(View.GONE);
            }
        }
    }
}
