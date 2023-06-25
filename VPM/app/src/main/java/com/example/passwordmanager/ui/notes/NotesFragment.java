package com.example.passwordmanager.ui.notes;

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

import com.example.passwordmanager.data.model.Notes;
import com.example.passwordmanager.databinding.FragmentNotesBinding;

import java.util.List;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NotesAdapter notesAdapter;
    private List<Notes> notesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotesViewModel notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerViewNotes;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        notesAdapter = new NotesAdapter(notesList); // Create your NotesAdapter
        recyclerView.setAdapter(notesAdapter);

        // Observe the notes list from the ViewModel
        notesViewModel.getNotes().observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                notesList = notes;
                notesAdapter.setNotes(notes);
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
