package com.example.passwordmanager.data.Firestore;
import androidx.annotation.NonNull;

import com.example.passwordmanager.data.model.Notes;
import com.example.passwordmanager.data.model.Password;
import com.example.passwordmanager.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireStoreServices {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void saveUser(String name, String mobile, OnUserSavedListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        db.collection("users")
                .document(uid)
                .set(new User(name, mobile))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // User data saved successfully
                        if (listener != null) {
                            listener.onUserSaved();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error occurred while saving user data
                        if (listener != null) {
                            listener.onUserSaveFailed(e);
                        }
                    }
                });
    }

    public interface OnUserSavedListener {
        void onUserSaved();
        void onUserSaveFailed(Exception e);
    }



    public static void savePassword(String domain, String password, OnPasswordSaveListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            db.collection("users")
                    .document(uid)
                    .collection("password")
                    .document()
                    .set(new Password(domain, password))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Password saved successfully
                            listener.onSuccess();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to save password
                            listener.onFailure(e.getMessage());
                        }
                    });
        } else {
            // Handle the case when the user is not signed in
        }
    }

    public interface OnPasswordSaveListener {
        void onSuccess();
        void onFailure(String error);
    }

    public static void saveNote(String title, String message, final OnNoteSavedListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            db.collection("users")
                    .document(uid)
                    .collection("notes")
                    .document()
                    .set(new Notes(title, message))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Note saved successfully
                            listener.onNoteSaved();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to save note
                            listener.onNoteSaveFailed(e);
                        }
                    });
        } else {
            // Handle the case when the user is not signed in
        }
    }

    public interface OnNoteSavedListener {
        void onNoteSaved();
        void onNoteSaveFailed(Exception e);
    }

    public static void getPasswords(OnPasswordsFetchListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            db.collection("users")
                    .document(uid)
                    .collection("password")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Password> passwords = new ArrayList<>();

                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Password password = documentSnapshot.toObject(Password.class);
                                passwords.add(password);
                            }

                            // Passwords fetched successfully
                            listener.onPasswordsFetched(passwords);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to fetch passwords
                            listener.onFetchFailure(e.getMessage());
                        }
                    });
        } else {
            // Handle the case when the user is not signed in
        }
    }
    public interface OnPasswordsFetchListener {
        void onPasswordsFetched(List<Password> passwords);
        void onFetchFailure(String error);
    }


}
