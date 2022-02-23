package com.geektech.newsapp40.ui.fragments;

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
import com.geektech.newsapp40.basic.BaseFragment;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;
import com.geektech.newsapp40.databinding.FragmentProfileBinding;


public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {


    @Override
    public FragmentProfileBinding bind() {
        return FragmentProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        ActivityResultLauncher<String> setImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Glide.with(binding.imageUsers).load(result).into(binding.imageUsers);

            }
        });
        binding.imageUsers.setOnClickListener(view -> {
            setImage.launch("image/*");
        });
    }


}