package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.adapter.DiseaseDataAdapter;
import com.ntpl.eyoga.adapter.MonthDataAdapter;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;

import java.util.ArrayList;

public class MonthlyReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextViewSemiBold title;
    ImageView back;
    ArrayList<DiseaseData> reportDatas;
    String[] months = {"January", "February", "March", "April", "May","June","July","August","September","October","November","December"};
    String[] monthsNo = {"01", "02", "03", "04", "05","06","07","08","09","10","11","12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);

        back.setOnClickListener(view -> finish());

//        title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MonthlyReportActivity.this, AndroidDatabaseManager.class);
//                startActivity(intent);
//            }
//        });

        RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(catLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        initMonths();
    }

    public void initMonths() {
        ArrayList<DiseaseData> listDatas = prepareData();
        MonthDataAdapter adapter = new MonthDataAdapter(this, listDatas);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DiseaseData> prepareData() {
        reportDatas = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            DiseaseData data = new DiseaseData();
            data.setDisease(months[i]);
            data.setMonth(monthsNo[i]);
            reportDatas.add(data);
        }
        return reportDatas;
    }
}