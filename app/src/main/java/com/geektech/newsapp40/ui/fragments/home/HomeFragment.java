package com.geektech.newsapp40.ui.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.utils.app.App;
import com.geektech.newsapp40.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    public FragmentHomeBinding bind() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        setAdaptersList();
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), (requestKey, result) -> {
            NewsModel news = (NewsModel) result.getSerializable("news");
            Log.e("Home", "text: " + news.getTitle() + " : " + news.getCreatedAt());
        });
    }

    private void setAdaptersList() {
        newsAdapter = new NewsAdapter();
        binding.recyclerHome.setAdapter(newsAdapter);
        App.database.newsDao().getAll().observe(getViewLifecycleOwner(), newsModels -> {
            newsAdapter.addItems(newsModels);
        });
    }

    private void initListeners() {
        binding.fab.setOnClickListener(view -> Navigation.findNavController(requireView()).navigate(R.id.newsFragment));
    }
}