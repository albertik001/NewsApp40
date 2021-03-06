package com.geektech.newsapp40.ui.fragments.news;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.room.model.NewsModel;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;
import com.geektech.newsapp40.utils.app.App;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class NewsFragment extends BaseFragment<FragmentNewsBinding> {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

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
            if (Objects.requireNonNull(binding.etTitle.getText()).toString().isEmpty()) {
                YoYo.with(Techniques.Bounce)
                        .duration(400)
                        .repeat(1)
                        .playOn(binding.nameTextMessage);
                binding.etTitle.setError("Заполните поле и повторите еще раз...");
            } else {
                binding.progressBar.setVisibility(View.VISIBLE);
                save();
            }
        });
    }

    private void save() {
        String text = Objects.requireNonNull(binding.etTitle.getText()).toString();
        NewsModel news = new NewsModel(text, System.currentTimeMillis(), "ALbert");
        saveToFirestore(news);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("news", news);
//        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        App.database.newsDao().insert(news);
    }

    private void saveToFirestore(NewsModel newsModel) {
        db.collection("news")
                .add(newsModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    close();
                    Toast.makeText(getContext(), "Успешно", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}