package com.example.passwordmanager.AddItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.passwordmanager.R;
import com.example.passwordmanager.databinding.ActivityPasswordBinding;
import com.example.passwordmanager.databinding.ActivitySecureNoteBinding;

public class SecureNoteActivity extends AppCompatActivity {
    private ActivitySecureNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecureNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Secure Notes");

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