package com.geektech.newsapp40;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.newsapp40.base.BaseFragment;
import com.geektech.newsapp40.data.Prefs;
import com.geektech.newsapp40.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {
    private static Prefs prefs;
    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;
    @Override
    public FragmentRegisterBinding bind() {
        return FragmentRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initListener();
        createCallback();

    }

    private void init() {
        prefs = new Prefs(requireContext());
        mAuth = FirebaseAuth.getInstance();
    }

    private void initListener() {
        binding.login.setOnClickListener(view1 -> {
            String phone = binding.number.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                binding.code.setError("Ошибка");
                return;
            }
            register(phone);
            binding.login.setVisibility(View.GONE);
            binding.okey.setVisibility(View.VISIBLE);
        });
    }

    public void createCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                binding.okey.setOnClickListener(view -> {
                    initcode(s);
                });
            }

            private void initcode(String s) {
                String code = binding.code.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    binding.code.setError("Ошибка");
                    return;
                }
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(s, code);
                signInWithPhoneAuthCredential(credential);
            }
        };
    }


    public void register(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            prefs.saveRegisterFragment();
                            NavController navController = Navigation.findNavController(requireActivity(), R.id.onBoardFragment);
                            navController.navigateUp();
                            Log.e("TAG", "onComplete: ");
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


}