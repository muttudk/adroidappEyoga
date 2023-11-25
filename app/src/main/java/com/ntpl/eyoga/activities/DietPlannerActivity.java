package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.adapter.DiseaseDataAdapter;
import com.ntpl.eyoga.helper.DiseaseData;

import java.util.ArrayList;

public class DietPlannerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back;
    ArrayList<DiseaseData> diseaseDatas;
    String[] diseases = {"Thyroid", "Arthritis", "Lower Back Pain", "PCOS (Polycystic Ovarian Syndrome)", "Diabetes","Stomach Disorder","Migraine","Liver problem","Depression","Kidney","High BP","Alzheimers","Joint Pain","Pregnancy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());

        RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(catLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        initDisease();

    }

    public void initDisease() {
        ArrayList<DiseaseData> listDatas = prepareData();
        DiseaseDataAdapter adapter = new DiseaseDataAdapter(this, listDatas);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DiseaseData> prepareData() {
        diseaseDatas = new ArrayList<>();
        for (int i = 0; i < diseases.length; i++) {
            DiseaseData data = new DiseaseData();
            data.setDisease(diseases[i]);
            diseaseDatas.add(data);
        }
        return diseaseDatas;
    }
}