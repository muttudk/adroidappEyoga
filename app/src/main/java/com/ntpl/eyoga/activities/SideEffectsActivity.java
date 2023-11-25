package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.TextViewRegular;

public class SideEffectsActivity extends AppCompatActivity {

    TextViewRegular sideEffects;
    String sideEffectsString;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_effects);

        Intent intent = getIntent();
        if (intent != null) {
            sideEffectsString = intent.getStringExtra("sideEffects");
        }

        sideEffects = findViewById(R.id.sideEffects);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());
        sideEffects.setText(sideEffectsString);
    }
}