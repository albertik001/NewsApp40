package com.geektech.newsapp40.ui.fragments.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.DashAdapter;
import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.databinding.FragmentDashboardBinding;
import com.geektech.newsapp40.ui.fragments.news.NewsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> {
    ArrayList<NewsModel> newsModelArrayList;
    private NewsAdapter dashAdapter;

    @Override
    public FragmentDashboardBinding bind() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedItem = null;
            switch (item.getItemId()){
                case R.id.navigation_dashboard:
                    sdafsd();
                    setAdapterList();
                    break;
            }
            return true;
        }
    };

    private void setAdapterList() {
        dashAdapter = new NewsAdapter();
        binding.recyclerDashboard.setAdapter(dashAdapter);
        dashAdapter.setNewsList(newsModelArrayList);
    }
    private  void  sdafsd(){
        getParentFragmentManager().setFragmentResultListener("rk_key", getViewLifecycleOwner(), ((requestKey, result) -> {
            NewsModel newsModel = (NewsModel) result.getSerializable("key");
            newsModelArrayList.add(newsModel);
        }));
    }


}