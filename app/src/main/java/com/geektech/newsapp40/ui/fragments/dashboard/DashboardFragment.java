package com.geektech.newsapp40.ui.fragments.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geektech.newsapp40.adapter.NewsAdapter;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.databinding.FragmentDashboardBinding;
import com.geektech.newsapp40.utils.interfaces.OnClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> implements OnClick {
    ArrayList<NewsModel> newsModelArrayList;
    private NewsAdapter dashAdapter;
    static FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public FragmentDashboardBinding bind() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        db.collection("news").orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            newsModelArrayList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                NewsModel model = document.toObject(NewsModel.class);
                                newsModelArrayList.add(model);
                            }
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            setAdapterList();

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setAdapterList() {
        dashAdapter = new NewsAdapter(this);
        binding.recyclerDashboard.setAdapter(dashAdapter);
        dashAdapter.addItems(newsModelArrayList);
    }


    @Override
    public void onClick(NewsModel newsModel) {
        
    }
}