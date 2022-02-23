package com.geektech.newsapp40.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.newsapp40.NewsModel.ClientModel;
import com.geektech.newsapp40.NewsModel.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.basic.BaseFragment;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        binding.btnSave.setOnClickListener(view -> {
            save();
        });
    }

    private void save() {
        String text = binding.etTitle.getText().toString();
        Date date = Calendar.getInstance().getTime();
        NewsModel news = new NewsModel(text, date.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}