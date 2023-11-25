package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.TextViewRegular;

public class BenefitsActivity extends AppCompatActivity {

    TextViewRegular benefits;
    String benefitsString;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefits);

        Intent intent = getIntent();
        if (intent != null) {
            benefitsString = intent.getStringExtra("benefits");
        }

        benefits = findViewById(R.id.benefits);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());
        benefits.setText(benefitsString);
    }

}