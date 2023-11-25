package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.adapter.MonthDataAdapter;
import com.ntpl.eyoga.adapter.WeeklyDataAdapter;
import com.ntpl.eyoga.helper.DiseaseData;

import java.util.ArrayList;

public class WeeklyReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back;
    ArrayList<DiseaseData> reportDatas;
//    String[] weeks = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
//    String[] weekNo = {"01", "02", "03", "04", "05","06","07"};
    String[] months = {"January", "February", "March", "April", "May","June","July","August","September","October","November","December"};
    String[] monthsNo = {"01", "02", "03", "04", "05","06","07","08","09","10","11","12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> finish());

        RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(catLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        initMonths();
    }

    public void initMonths() {
        ArrayList<DiseaseData> listDatas = prepareData();
        WeeklyDataAdapter adapter = new WeeklyDataAdapter(this, listDatas);
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