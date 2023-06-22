package com.example.passwordmanager.data.Firestore;
import com.example.passwordmanager.data.model.Notes;
import com.example.passwordmanager.data.model.Password;
import com.example.passwordmanager.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireStoreServices {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void saveUser(String name, String mobile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        
        db.collection("users")
                .document(uid)
                .set(new User(name, mobile));
    }

    public static void savePassword(String domain, String password) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            db.collection("users")
                    .document(uid)
                    .collection("password")
                    .document()
                    .set(new Password(domain, password));
        } else {
            // Handle the case when the user is not signed in
        }
    }

    public static void saveNote(String title, String message) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();

            db.collection("users")
                    .document(uid)
                    .collection("notes")
                    .document()
                    .set(new Notes(title, message));
        } else {
            // Handle the case when the user is not signed in
        }
    }



}
