package com.geektech.newsapp40.ui.fragments.onboard;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.geektech.newsapp40.R;
import com.geektech.newsapp40.adapter.BoardAdapter;
import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.Prefs;
import com.geektech.newsapp40.databinding.FragmentOnBoardBinding;
import com.geektech.newsapp40.utils.interfaces.OnClick;
import com.geektech.newsapp40.ui.animate.ZoomOutPageTransformer;


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
                    binding.skipped.setOnClickListener(view1 -> onClick());
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
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}