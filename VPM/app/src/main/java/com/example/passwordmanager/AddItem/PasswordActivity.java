package com.example.passwordmanager.AddItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.passwordmanager.MainActivity2;
import com.example.passwordmanager.R;
import com.example.passwordmanager.data.Firestore.FireStoreServices;
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

        binding.buttonSave.setOnClickListener(view -> {
            String domain = binding.editTextDomain.getText().toString();
            String password = binding.editTextPassword.getText().toString();

            // Check if domain and password are null
            if (domain.isEmpty() && password.isEmpty()) {
                // Both domain and password are null
                Toast.makeText(PasswordActivity.this, "Domain and Password are empty", Toast.LENGTH_SHORT).show();
            } else if (domain.isEmpty()) {
                // Only domain is null
                Toast.makeText(PasswordActivity.this, "Domain is empty", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                // Only password is null
                Toast.makeText(PasswordActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            } else {
                // Both domain and password have values

                // Show loading indicator
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.buttonSave.setEnabled(false);

                // Save password in Firestore
                FireStoreServices.savePassword(domain, password, new FireStoreServices.OnPasswordSaveListener() {
                    @Override
                    public void onSuccess() {
                        // Hide loading indicator
                        binding.progressBar.setVisibility(View.GONE);
                        binding.buttonSave.setEnabled(true);

                        // Start MainActivity2
                        Intent intent = new Intent(PasswordActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(String error) {
                        // Hide loading indicator
                        binding.progressBar.setVisibility(View.GONE);
                        binding.buttonSave.setEnabled(true);

                        // Show error message or handle the error
                        Toast.makeText(PasswordActivity.this, "Failed to save password: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}