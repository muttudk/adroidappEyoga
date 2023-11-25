package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandler;
import com.ntpl.eyoga.helper.SessionManager;

public class LoginActivity extends AppCompatActivity {

    ImageView back;
    TextInputEditText fullName, password;
    String fullNameString, passwordString;
    TextViewSemiBold forgotPassword;
    ButtonSemiBold login;
    SQLiteHandler db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_login);

        db = new SQLiteHandler(this);
        sessionManager = new SessionManager(this);

        back = findViewById(R.id.back);
        fullName = findViewById(R.id.fullName);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);
        login = findViewById(R.id.login);

        back.setOnClickListener(view -> finish());

        login.setOnClickListener(view -> {

            if(validate()){

//                    if (db.checkUserExist(fullNameString, passwordString)) {
                    if (db.checkUserExist(fullNameString)) {
                        String password = db.getPassword(fullNameString);
                        if(password.equals(passwordString)){
//                            Toast.makeText(this, "Sign In Successfully", Toast.LENGTH_SHORT).show();

                            String emailId = db.getEmailId(fullNameString);

                            sessionManager.createLoginSession(fullNameString, emailId);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(this, "Kindly check the password", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
            }

        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        fullNameString = fullName.getText().toString().trim();
        passwordString = password.getText().toString().trim();

        if (fullNameString.isEmpty()) {
            fullName.setError("Enter your Full Name");
            fullName.requestFocus();
            valid = false;
        }
        else if(passwordString.isEmpty())
        {
            password.setError("Enter a Password");
            password.requestFocus();
            valid = false;
        }
        else {
            fullName.setError(null);
            password.setError(null);
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();
    }
}