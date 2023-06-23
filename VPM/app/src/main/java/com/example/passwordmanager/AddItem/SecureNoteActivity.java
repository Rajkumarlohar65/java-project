package com.example.passwordmanager.AddItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.passwordmanager.MainActivity2;
import com.example.passwordmanager.R;
import com.example.passwordmanager.data.Firestore.FireStoreServices;
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

        binding.saveButton.setOnClickListener(view -> {
            String title = binding.editTextTitle.getText().toString();
            String message = binding.noteEditText.getText().toString();

            // Show loading indicator
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.saveButton.setEnabled(false);

            // Save note in Firestore
            FireStoreServices.saveNote(title, message, new FireStoreServices.OnNoteSavedListener() {
                @Override
                public void onNoteSaved() {
                    // Hide loading indicator
                    binding.progressBar.setVisibility(View.GONE);
                    binding.saveButton.setEnabled(true);

                    // Start MainActivity2
                    Intent intent = new Intent(SecureNoteActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onNoteSaveFailed(Exception e) {
                    // Hide loading indicator
                    binding.progressBar.setVisibility(View.GONE);
                    binding.saveButton.setEnabled(true);

                    // Show error message or handle the error
                    Toast.makeText(SecureNoteActivity.this, "Failed to save note: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        });

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