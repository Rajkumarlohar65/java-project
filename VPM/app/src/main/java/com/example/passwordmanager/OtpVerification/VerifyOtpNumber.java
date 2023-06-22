package com.example.passwordmanager.OtpVerification;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.passwordmanager.MainActivity2;
import com.example.passwordmanager.databinding.Activity4VerifyOtpNumberBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtpNumber extends AppCompatActivity {
    private Activity4VerifyOtpNumberBinding binding;
    FirebaseAuth mAuth;
    String otpId;
    String country_code;
    String mobileNumber;
    String enteredOtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Activity4VerifyOtpNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        country_code = intent.getStringExtra("code");
        String mb = intent.getStringExtra("number");
        mobileNumber = country_code + mb;
        binding.textViewNumber.setText(mobileNumber);

        initiateOtp();

        binding.textViewResendOtp.setOnClickListener(view -> {
            binding.textViewResendOtp.setEnabled(false); // Disable the button
            initiateOtp();
            Toast.makeText(VerifyOtpNumber.this, "Sending New OTP", Toast.LENGTH_SHORT).show();

            // Enable the button after 1 minute
            binding.textViewResendOtp.postDelayed(() -> {
                binding.textViewResendOtp.setEnabled(true); // Enable the button
            }, 60000); // 60000 milliseconds = 1 minute

        });


        binding.progressBar3.setVisibility(View.INVISIBLE);

        binding.btnVerifyOtp.setOnClickListener(view -> {

            if (!binding.inputNumber1.getText().toString().trim().isEmpty() && !binding.inputNumber2.getText().toString().trim().isEmpty() && !binding.inputNumber3.getText().toString().trim().isEmpty() && !binding.inputNumber4.getText().toString().trim().isEmpty() && !binding.inputNumber5.getText().toString().trim().isEmpty() && !binding.inputNumber6.getText().toString().trim().isEmpty()) {

                binding.btnVerifyOtp.setVisibility(View.INVISIBLE);
                binding.progressBar3.setVisibility(View.VISIBLE);

                enteredOtp = binding.inputNumber1.getText().toString() + binding.inputNumber2.getText().toString() + binding.inputNumber3.getText().toString() + binding.inputNumber4.getText().toString() + binding.inputNumber5.getText().toString() + binding.inputNumber6.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpId, enteredOtp);
                signInWithPhoneAuthCredential(credential);

            }
            else{
                Toast.makeText(VerifyOtpNumber.this, "Please enter all number !", Toast.LENGTH_SHORT).show();
            }
        });

        numberOtpMove();
    }

    private void numberOtpMove() {
        binding.inputNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber2.requestFocus();
                }
                else {
                    binding.inputNumber1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inputNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber3.requestFocus();
                }
                else {
                    binding.inputNumber1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inputNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber4.requestFocus();
                }
                else {
                    binding.inputNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inputNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber5.requestFocus();
                }
                else {
                    binding.inputNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inputNumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber6.requestFocus();
                }
                else {
                    binding.inputNumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.inputNumber6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    binding.inputNumber6.requestFocus();
                }
                else {
                    binding.inputNumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initiateOtp(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobileNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(VerifyOtpNumber.this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpId = s;
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOtpNumber.this, "verification failed", Toast.LENGTH_SHORT).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        binding.btnVerifyOtp.setVisibility(View.VISIBLE);
                        binding.progressBar3.setVisibility(View.INVISIBLE);
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        Toast.makeText(VerifyOtpNumber.this, "Verification Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerifyOtpNumber.this, MainActivity2.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        binding.btnVerifyOtp.setVisibility(View.VISIBLE);
                        binding.progressBar3.setVisibility(View.INVISIBLE);
                        Toast.makeText(VerifyOtpNumber.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                });
    }
}