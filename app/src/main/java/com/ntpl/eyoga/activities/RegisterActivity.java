package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandler;
import com.ntpl.eyoga.helper.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    ImageView back;
    TextInputEditText fullName, emailID, password, age;
    String fullNameString, emailIDString, passwordString, ageString, genderString;
    RadioGroup radioGender;
    ButtonSemiBold register;
    SQLiteHandler db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_register);

        db = new SQLiteHandler(this);
        sessionManager = new SessionManager(this);

        back = findViewById(R.id.back);
        fullName = findViewById(R.id.fullName);
        emailID = findViewById(R.id.emailID);
        password = findViewById(R.id.password);
        age = findViewById(R.id.age);
        radioGender = findViewById(R.id.radioGender);
        register = findViewById(R.id.register);

        back.setOnClickListener(view -> finish());

        radioGender.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId)
            {
                case R.id.male:
                    genderString = "Male";
                    break;

                case R.id.female:
                    genderString = "Female";
                    break;
            }
        });


        register.setOnClickListener(view -> {

            if(validate()){

                if(db.checkUserExist(fullNameString, passwordString)){
                    Toast.makeText(RegisterActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.insertUser(fullNameString,emailIDString,passwordString,ageString,genderString);
                    Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();

                    sessionManager.createLoginSession(fullNameString, emailIDString);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }

        });
    }

    public boolean validate() {
        boolean valid = true;

        fullNameString = fullName.getText().toString().trim();
        emailIDString = emailID.getText().toString().trim();
        passwordString = password.getText().toString().trim();
        ageString = age.getText().toString().trim();

        if (fullNameString.isEmpty()) {
            fullName.setError("Enter your Full Name");
            fullName.requestFocus();
            valid = false;
        }
        else if(emailIDString.isEmpty())
        {
            emailID.setError("Enter your Email ID");
            emailID.requestFocus();
            valid = false;
        }
        else if(passwordString.isEmpty())
        {
            password.setError("Enter a Password");
            password.requestFocus();
            valid = false;
        }
        else if(ageString.isEmpty())
        {
            age.setError("Enter your Age");
            age.requestFocus();
            valid = false;
        }
        else if(radioGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(RegisterActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else {
            fullName.setError(null);
            emailID.setError(null);
            password.setError(null);
            age.setError(null);
        }
        return valid;
    }
}