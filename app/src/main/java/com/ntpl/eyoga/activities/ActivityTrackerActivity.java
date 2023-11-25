package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.helper.SQLiteHandlerReport;

import java.util.concurrent.TimeUnit;

public class ActivityTrackerActivity extends AppCompatActivity {

    TextViewRegular totalDuration,totalExercise;
    SQLiteHandlerReport dbReport;
    String timeSpentSum, totalExc;
    ImageView back;
    ButtonSemiBold monthlyReport,weeklyReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        dbReport = new SQLiteHandlerReport(this);

        totalDuration = findViewById(R.id.totalDuration);
        totalExercise = findViewById(R.id.totalExercise);
        back = findViewById(R.id.back);
        monthlyReport = findViewById(R.id.monthlyReport);
        weeklyReport = findViewById(R.id.weeklyReport);

        back.setOnClickListener(view -> finish());

        Cursor cursorTime = dbReport.getTimeSpentSum();
        if(cursorTime != null && cursorTime.getCount() >0) {
            if (cursorTime.moveToFirst()) {
                do {
                    timeSpentSum = cursorTime.getString(0);
                } while (cursorTime.moveToNext());
            }
        }

        monthlyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityTrackerActivity.this, MonthlyReportActivity.class);
                startActivity(intent);
            }
        });

        weeklyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityTrackerActivity.this, WeeklyReportActivity.class);
                startActivity(intent);
            }
        });

        Cursor cursorAsana = dbReport.getDistinctAsana();
        if(cursorTime != null && cursorTime.getCount() >0) {
            if (cursorTime.moveToFirst()) {
                do {
                    totalExc = cursorAsana.getString(0);
                } while (cursorTime.moveToNext());
            }
        }

        if(timeSpentSum != null) {
//            long toHours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum));
//            long toMinutes = TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum));
//            long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(timeSpentSum));

            long toDays = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
            long toHours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeSpentSum)));
            long toMinutes = TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(timeSpentSum)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)));

//            if (toHours > 0) {
                totalDuration.setText(twoDigitString(toDays) + "d " +twoDigitString(toHours) + "h " + twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");
//            } else {
//                totalDuration.setText(toMinutes + "m " + toSeconds + "s");
//            }

            totalExercise.setText(totalExc);

            Log.d("TImeSpent", toHours + "h " + toMinutes + "m " + toSeconds + "s");
        }
        else{
            totalDuration.setText("00d 00h 00m 00s");
            totalExercise.setText("00");
        }

    }

    private String twoDigitString(long number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
}