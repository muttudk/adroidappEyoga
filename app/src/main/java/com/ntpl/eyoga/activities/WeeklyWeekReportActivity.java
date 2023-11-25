package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.adapter.WeeklyDataAdapter;
import com.ntpl.eyoga.adapter.WeeklyWeekDataAdapter;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;

import java.util.ArrayList;

public class WeeklyWeekReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back;
    TextViewSemiBold title;
    ArrayList<DiseaseData> reportDatas;
    String[] week = {"First Week (01 to 07)", "Second Week (08 to 14)", "Third Week (15 to 21)", "Fourth Week (22 to 31)"};
    String[] weekNo = {"01", "02", "03", "04"};
    String month,monthNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_week_report);

        Intent intent = getIntent();
        if (intent != null) {
            month = intent.getStringExtra("month");
            monthNo = intent.getStringExtra("monthNo");
        }

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);

        title.setText(month);

        back.setOnClickListener(view -> finish());

        RecyclerView.LayoutManager catLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(catLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        initMonths();
    }

    public void initMonths() {
        ArrayList<DiseaseData> listDatas = prepareData();
        WeeklyWeekDataAdapter adapter = new WeeklyWeekDataAdapter(this, listDatas, monthNo);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DiseaseData> prepareData() {
        reportDatas = new ArrayList<>();
        for (int i = 0; i < week.length; i++) {
            DiseaseData data = new DiseaseData();
            data.setDisease(week[i]);
            data.setMonth(weekNo[i]);
            reportDatas.add(data);
        }
        return reportDatas;
    }
}