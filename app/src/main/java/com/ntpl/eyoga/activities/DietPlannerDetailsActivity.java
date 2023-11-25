package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandlerDisease;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DietPlannerDetailsActivity extends AppCompatActivity {

    ImageView back;
    SQLiteHandlerDisease db;
    TextViewSemiBold title;
    TextViewRegular diet;
    String diseaseName,dietString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner_details);

        db = new SQLiteHandlerDisease(this);

        back = findViewById(R.id.back);
        diet = findViewById(R.id.diet);
        title = findViewById(R.id.title);

        Intent intent = getIntent();
        if (intent != null) {
            diseaseName = intent.getStringExtra("diseaseName");
        }

        back.setOnClickListener(view -> {
            finish();
        });

        Cursor cursor = db.getSingleRow(diseaseName);
        if(cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    dietString = cursor.getString(2);
                } while (cursor.moveToNext());
            }
        }

        title.setText(diseaseName);
        diet.setText(dietString);
    }
}