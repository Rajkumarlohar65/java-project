package com.example.passwordmanager.data.model;

public class Notes {
    private String title;
    private String message;

    public Notes() {
        // Required empty constructor for Firestore deserialization
    }

    public Notes(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}