package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.helper.SessionManager;

import java.util.HashMap;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000;
    SessionManager sessionManager;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetails();
        fullName = user.get(SessionManager.KEY_FULL_NAME);

        new Handler().postDelayed(() -> {

            if(sessionManager.isLoggedIn() && fullName != null){
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent1 = new Intent(SplashScreenActivity.this, FirstActivity.class);
                startActivity(intent1);
            }

        }, SPLASH_DURATION);
    }
}