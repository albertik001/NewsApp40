package com.geektech.newsapp40.ui.fragments.news;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.utils.app.App;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class    NewsFragment extends BaseFragment<FragmentNewsBinding> {

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
        NewsModel news = new NewsModel(text, System.currentTimeMillis(), "ALbert");
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        App.database.newsDao().insert(news);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}