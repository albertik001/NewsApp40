package com.geektech.newsapp40.ui.fragments.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import androidx.navigation.Navigation;

import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.utils.app.App;
import com.geektech.newsapp40.databinding.FragmentHomeBinding;
import com.geektech.newsapp40.utils.interfaces.OnClick;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements OnClick {
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
//        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), (requestKey, result) -> {
//            NewsModel news = (NewsModel) result.getSerializable("news");
//            Log.e("Home", "text: " + news.getTitle() + " : " + news.getCreatedAt());
//        });
    }


    private void setAdaptersList() {
        newsAdapter = new NewsAdapter(this);
        binding.recyclerHome.setAdapter(newsAdapter);
        App.database.newsDao().getAll().observe(getViewLifecycleOwner(), newsModels -> {
            newsAdapter.addItems(newsModels);
        });
    }

    private void initListeners() {
        binding.fab.setOnClickListener(view -> Navigation.findNavController(requireView()).navigate(R.id.newsFragment));
    }

    @Override
    public void onClick(NewsModel newsModel) {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setTitle("Подтвердите выбор");
        materialAlertDialogBuilder.setMessage("Вы точно хотите удалить лист?");
        materialAlertDialogBuilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("dsfsdfsdfsfsdf");;
            }
        });
        materialAlertDialogBuilder.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

    }
}