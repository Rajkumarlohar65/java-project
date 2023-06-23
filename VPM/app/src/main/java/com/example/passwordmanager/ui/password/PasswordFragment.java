package com.example.passwordmanager.ui.password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordmanager.data.model.Password;
import com.example.passwordmanager.databinding.FragmentPasswordBinding;

import java.util.List;

public class PasswordFragment extends Fragment {

    private FragmentPasswordBinding binding;
    private com.example.passwordmanager.ui.password.PasswordAdapter passwordAdapter;
    private List<Password> passwordList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PasswordViewModel passwordViewModel =
                new ViewModelProvider(this).get(PasswordViewModel.class);

        binding = FragmentPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerViewPasswords;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        passwordAdapter = new com.example.passwordmanager.ui.password.PasswordAdapter(passwordList); // Create your PasswordAdapter
        recyclerView.setAdapter(passwordAdapter);

        // Observe the password list from the ViewModel
        passwordViewModel.getPasswords().observe(getViewLifecycleOwner(), new Observer<List<Password>>() {
            @Override
            public void onChanged(List<Password> passwords) {
                passwordList = passwords;
                passwordAdapter.setPasswords(passwords);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
