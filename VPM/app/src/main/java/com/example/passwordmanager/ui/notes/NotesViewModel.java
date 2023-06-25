package com.example.passwordmanager.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passwordmanager.data.Firestore.FireStoreServices;
import com.example.passwordmanager.data.model.Notes;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private final MutableLiveData<List<Notes>> notesLiveData;

    public NotesViewModel() {
        notesLiveData = new MutableLiveData<>();
        // Call a method to fetch the notes and update the LiveData
        fetchNotes();
    }

    public LiveData<List<Notes>> getNotes() {
        return notesLiveData;
    }

    private void fetchNotes() {
        FireStoreServices.getNotes(new FireStoreServices.OnNotesFetchListener() {
            @Override
            public void onNotesFetched(List<Notes> notes) {
                notesLiveData.setValue(notes);
            }

            @Override
            public void onFetchFailure(String error) {
                // Handle the error case
            }
        });
    }
}
