package com.example.passwordmanager.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.passwordmanager.MainActivity2;
import com.example.passwordmanager.R;
import com.example.passwordmanager.welcome_Screen.WelcomePage;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_splashscreen);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                Intent intent = new Intent(SplashScreen.this, WelcomePage.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashScreen.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }

        }, 2000);

    }
}