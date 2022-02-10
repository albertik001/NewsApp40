package com.geektech.newsapp40;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;

    ActivityResultLauncher<String> setImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            Glide.with(binding.imageUsers).load(result).into(binding.imageUsers);

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageUsers.setOnClickListener(v -> {
            setImage.launch("image/*");

        });
    }

    private void galleryIntent() {
        ActivityResultLauncher<String> setImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Glide.with(binding.imageUsers).load(result).into(binding.imageUsers);

            }
        });
    }
}