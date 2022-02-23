package com.geektech.newsapp40;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.newsapp40.adapter.BoardAdapter;
import com.geektech.newsapp40.basic.BaseFragment;
import com.geektech.newsapp40.databinding.FragmentNewsBinding;
import com.geektech.newsapp40.databinding.FragmentOnBoardBinding;
import com.geektech.newsapp40.databinding.ItemBoardBinding;
import com.google.android.material.button.MaterialButton;


public class OnBoardFragment extends BaseFragment<FragmentOnBoardBinding> implements OnClick {

    @Override
    public FragmentOnBoardBinding bind() {
        return FragmentOnBoardBinding.inflate(getLayoutInflater());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapters();
        viewPagerCallBack();
        backPressed();
    }

    private void initAdapters() {
        BoardAdapter adapter = new BoardAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.viewPager);
        binding.viewPager.setPageTransformer(new ZoomOutPageTransformer());
    }

    private void viewPagerCallBack() {
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.skipped.setVisibility(View.GONE);
                } else {
                    binding.skipped.setVisibility(View.VISIBLE);
                    binding.skipped.setOnClickListener(view1 -> {
                        onClick();
                    });
                }
            }
        });
    }

    private void backPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }


    @Override
    public void onClick() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}