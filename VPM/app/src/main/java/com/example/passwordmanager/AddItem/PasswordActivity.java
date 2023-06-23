package com.example.passwordmanager.AddItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.passwordmanager.R;
import com.example.passwordmanager.databinding.ActivityAddItemBinding;
import com.example.passwordmanager.databinding.ActivityMainBinding;
import com.example.passwordmanager.databinding.ActivityPasswordBinding;

public class PasswordActivity extends AppCompatActivity {
    private ActivityPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Password");

        // Enable the back button in the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}