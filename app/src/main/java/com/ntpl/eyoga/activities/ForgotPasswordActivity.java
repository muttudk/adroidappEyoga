package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandler;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputEditText emailID,newPassword,confirmPassword;
    ButtonSemiBold check,update;
    LinearLayout passwordLinear;
    SQLiteHandler db;
    String emailIDString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_forgot_password);

        db = new SQLiteHandler(this);

        emailID = findViewById(R.id.emailID);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        check = findViewById(R.id.check);
        update = findViewById(R.id.update);
        passwordLinear = findViewById(R.id.passwordLinear);

        passwordLinear.setVisibility(View.GONE);

        check.setOnClickListener(view -> {
            emailIDString = emailID.getText().toString().trim();
            if(emailIDString.isEmpty()){
                passwordLinear.setVisibility(View.GONE);
                emailID.setError("Enter your Email ID");
                emailID.requestFocus();
            }
            else if (db.checkEmailExist(emailIDString)){

                passwordLinear.setVisibility(View.VISIBLE);

            }
            else{
                passwordLinear.setVisibility(View.GONE);
                Toast.makeText(this, "Enter valid Email ID", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(view -> {

            String newPasswordString = newPassword.getText().toString().trim();
            String confirmPasswordString = confirmPassword.getText().toString().trim();

            if(newPasswordString.isEmpty()){
                newPassword.setError("Enter a Password");
                newPassword.requestFocus();
            }
            else if(confirmPasswordString.isEmpty()){
                confirmPassword.setError("Enter Confirm Password");
                confirmPassword.requestFocus();
            }
            else if(!confirmPasswordString.equals(newPasswordString)){
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
            }
            else{
                db.updatePassword(emailIDString, newPasswordString);
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });
    }
}