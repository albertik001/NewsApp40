package com.geektech.newsapp40.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.newsapp40.NewsModel.ClientModel;
import com.geektech.newsapp40.NewsModel.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.basic.BaseFragment;
import com.geektech.newsapp40.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private NewsAdapter newsAdapter;
    ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();

    @Override
    public FragmentHomeBinding bind() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        setAdaptersList();
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NewsModel news = (NewsModel) result.getSerializable("news");
                newsAdapter.addItem(news);
                Log.e("Home", String.format("text: " + news.getTitle() + " : " + news.getCreatedAt()));
            }
        });
    }

    private void setAdaptersList() {
        newsModelArrayList = ClientModel.getNewsList();
        newsAdapter = new NewsAdapter();
        newsAdapter.setNewsList(newsModelArrayList);
        binding.recyclerHome.setAdapter(newsAdapter);
    }

    private void initListeners() {
        binding.fab.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(R.id.newsFragment);
        });
    }
}