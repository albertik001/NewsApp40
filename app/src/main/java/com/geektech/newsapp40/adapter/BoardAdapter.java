package com.geektech.newsapp40.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.NewsModel.ClientModel;
import com.geektech.newsapp40.OnClick;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private OnClick onClick;
    private String[] titles = new String[]{"Fast Food", "Fast Delivery", "Easy Payment"};
    public static int[] imageList = new int[]{R.drawable.pngwing_com_2_, R.drawable.pngwing_com_3_, R.drawable.pngwing_com_1_};


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
        return titles.length;
    }


    public class BoardViewHolder extends RecyclerView.ViewHolder {
        private ItemBoardBinding binding;

        public BoardViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.textThemeBoard.setText(titles[position]);
            binding.textBoardDescription.setText(ClientModel.titleDescription[position]);
            binding.imageBoard.setImageResource(imageList[position]);
      //      binding.imageBoard.setImageResource(ClientModel.imageList[position]);
            if (position == getItemCount() - 1) {
                binding.btnGetStart.setVisibility(View.VISIBLE);
                binding.btnGetStart.setOnClickListener(view -> {
                    onClick.onClick();
                });
            } else {
                binding.btnGetStart.setVisibility(View.GONE);
            }
        }
    }
}
