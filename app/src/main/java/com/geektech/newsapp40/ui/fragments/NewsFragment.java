package com.geektech.newsapp40.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;

import com.geektech.newsapp40.data.App;
import com.geektech.newsapp40.NewsModel.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.basic.BaseFragment;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    @Override
    public FragmentNewsBinding bind() {
        return FragmentNewsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.btnSave.setOnClickListener(view -> save());
    }

    private void save() {
        String text = Objects.requireNonNull(binding.etTitle.getText()).toString();
        Date date = Calendar.getInstance().getTime();
        NewsModel news = new NewsModel(text, date.toString(), "ALbert");
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        App.dataBase.newsDao().insertNews(news);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}