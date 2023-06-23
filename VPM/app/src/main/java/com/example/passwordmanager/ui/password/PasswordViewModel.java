package com.example.passwordmanager.ui.password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passwordmanager.data.Firestore.FireStoreServices;
import com.example.passwordmanager.data.model.Password;

import java.util.List;

public class PasswordViewModel extends ViewModel {

    private final MutableLiveData<List<Password>> passwordsLiveData;

    public PasswordViewModel() {
        passwordsLiveData = new MutableLiveData<>();
        // Call a method to fetch the passwords and update the LiveData
        fetchPasswords();
    }

    public LiveData<List<Password>> getPasswords() {
        return passwordsLiveData;
    }

    private void fetchPasswords() {
        FireStoreServices.getPasswords(new FireStoreServices.OnPasswordsFetchListener() {
            @Override
            public void onPasswordsFetched(List<Password> passwords) {
                passwordsLiveData.setValue(passwords);
            }

            @Override
            public void onFetchFailure(String error) {
                // Handle the error case
            }
        });
    }
}
