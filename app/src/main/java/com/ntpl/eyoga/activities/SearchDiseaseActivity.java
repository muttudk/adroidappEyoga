package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.adapter.SearchDiseaseAdapter;
import com.ntpl.eyoga.helper.SQLiteHandlerDisease;

import java.util.ArrayList;
import java.util.List;

public class SearchDiseaseActivity extends AppCompatActivity {

    ImageView back;
    AutoCompleteTextView searchDisease;
    ArrayList<String> searchDiseaseList;
    SQLiteHandlerDisease db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_disease);

        db = new SQLiteHandlerDisease(this);

        searchDisease = findViewById(R.id.searchDisease);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());

        searchDiseaseList = new ArrayList<>();
        searchDiseaseList.add("Thyroid");
        searchDiseaseList.add("Arthritis");
        searchDiseaseList.add("Lower Back Pain");
        searchDiseaseList.add("PCOS (Polycystic Ovarian Syndrome)");
        searchDiseaseList.add("Diabetes");
        searchDiseaseList.add("Stomach Disorder");
        searchDiseaseList.add("Migraine");
        searchDiseaseList.add("Liver problem");
        searchDiseaseList.add("Depression");
        searchDiseaseList.add("Kidney");
        searchDiseaseList.add("High BP");
        searchDiseaseList.add("Alzheimers");
        searchDiseaseList.add("Joint Pain");
        searchDiseaseList.add("Pregnancy");

        searchDisease.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                searchDisease.showDropDown();

        });

        searchDisease.setOnTouchListener((paramView, paramMotionEvent) -> {
            // TODO Auto-generated method stub
            searchDisease.showDropDown();
            searchDisease.requestFocus();
            return false;
        });

        searchDisease.setOnItemClickListener((adapterView, view, i, l) -> {

            String disease = searchDiseaseList.get(i);
            searchDisease.setText(disease);


            searchDisease.setSelection(searchDisease.getText().length());

                Intent intent = new Intent(SearchDiseaseActivity.this, DiseaseDetailsActivity.class);
                intent.putExtra("diseaseName",disease);
                startActivity(intent);

        });



        searchDisease.setThreshold(1);

        searchDisease.setAdapter(new SearchDiseaseAdapter(SearchDiseaseActivity.this, searchDiseaseList));
    }
}