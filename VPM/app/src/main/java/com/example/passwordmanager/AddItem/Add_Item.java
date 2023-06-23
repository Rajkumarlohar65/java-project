package com.example.passwordmanager.AddItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.passwordmanager.databinding.ActivityAddItemBinding;

import java.util.ArrayList;

public class Add_Item extends AppCompatActivity {
    private ActivityAddItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar2);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Password");
        arrayList.add("Secure note");
        arrayList.add("Address");
        arrayList.add("Payment card");
        arrayList.add("Wi-fi password");
        arrayList.add("Bank account");
        arrayList.add("Drivers license");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Add_Item.this, android.R.layout.simple_list_item_1, arrayList);
        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            // Perform any desired action with the selected item

            // Start a new activity based on the selected item
            Intent intent;
            switch (selectedItem) {
                case "Password":
                    intent = new Intent(Add_Item.this, PasswordActivity.class);
                    startActivity(intent);
                    break;
                case "Secure note":
                    intent = new Intent(Add_Item.this, SecureNoteActivity.class);
                    startActivity(intent);
                    break;
                // Add cases for other items as needed
                default:
                    Toast.makeText(Add_Item.this, "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}