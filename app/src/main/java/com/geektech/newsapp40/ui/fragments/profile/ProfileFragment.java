package com.geektech.newsapp40.ui.fragments.profile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.geektech.newsapp40.R;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.Prefs;
import com.geektech.newsapp40.databinding.FragmentProfileBinding;

import java.util.Objects;


public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    private Prefs prefs;

    @Override
    public FragmentProfileBinding bind() {
        return FragmentProfileBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Prefs(requireContext());
        initListener();
        prefsBase(prefs);
        textListener();

    }

    private void textListener() {
        binding.etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.saveUsernameProfile(Objects.requireNonNull(binding.etUsername.getText()).toString());
            }
        });
    }

    private void prefsBase(Prefs prefs) {
        if (prefs.isImageUsers() != null && prefs.isUsernameProfile() != null) {
            binding.etUsername.setText(prefs.isUsernameProfile());
            Glide.with(binding.imageUsers).load(prefs.isImageUsers()).circleCrop().into(binding.imageUsers);
        }
        if (prefs.isImageUsers()==null){
            binding.imageUsers.setImageResource(R.drawable.pngwing_com_1_);
        }
    }

    private void initListener() {
        ActivityResultLauncher<String> setImage = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            Glide.with(binding.imageUsers).load(result).circleCrop().into(binding.imageUsers);
            prefs.saveImageUsers(result);
        });
        binding.imageUsers.setOnClickListener(view -> setImage.launch("image/*"));
    }


}