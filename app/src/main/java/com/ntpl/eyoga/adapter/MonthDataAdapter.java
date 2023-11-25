package com.ntpl.eyoga.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.activities.DietPlannerDetailsActivity;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;
import com.ntpl.eyoga.helper.SQLiteHandlerReport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonthDataAdapter extends RecyclerView.Adapter<MonthDataAdapter.ViewHolder>  {

    private List<DiseaseData> diseaseData;
    private final Context context;

    public MonthDataAdapter(Context context,List<DiseaseData> diseaseData) {
        this.diseaseData = diseaseData;
        this.context = context;
    }

    @Override
    public MonthDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_disease, viewGroup, false);
        return new MonthDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MonthDataAdapter.ViewHolder viewHolder, int i) {
        final DiseaseData data = diseaseData.get(i);

        viewHolder.disease.setText(data.getDisease());

        viewHolder.linearLayout.setOnClickListener(view -> {

            Dialog dialogBuilder = new Dialog(context);
            dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            final View dialogView = LayoutInflater.from(context).inflate(R.layout.popup_report, null);
            dialogBuilder.setContentView(dialogView);
//            dialogBuilder.setCancelable(false);
//            dialogBuilder.setCanceledOnTouchOutside(false);

            TextViewRegular totalDuration = dialogView.findViewById(R.id.totalDuration);
            TextViewRegular totalExercise = dialogView.findViewById(R.id.totalExercise);
            String timeSpentSum = "00h 00m 00s", totalExc = "00";

//            if(data.getMonth().equalsIgnoreCase("05")) {
//                Cursor cursorTime = viewHolder.dbReport.getTimeSpentSum();
//            String fromDate = "01-"+data.getMonth()+"-2021";
            String fromDate = "2021-"+data.getMonth()+"-01";
            String toDate = "2021-"+data.getMonth()+"-31";
            long timestampFromDate = 0,timestampToDate=0;

//            String strToDate = toDate;
//            DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
//            Date date1 = null;
//            try {
//
//                date1 = (Date)formatter1.parse(strToDate);
//                long output = date1.getTime()/1000L;
//                String str = Long.toString(output);
//                timestampToDate = Long.parseLong(str) * 1000;
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

//            String strFromDate = fromDate;
//            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            Date date = null;
//            try {
//
//                date = (Date)formatter.parse(strFromDate);
//                long output = date.getTime()/1000L;
//                String str = Long.toString(output);
//                timestampFromDate = Long.parseLong(str) * 1000;
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

                Cursor cursorTime = viewHolder.dbReport.getMonthTimeSpentSum(fromDate, toDate);
//                Cursor cursorTime = viewHolder.dbReport.getMonthTimeSpentSum("2021-05-01", "2021-05-31");
//                Cursor cursorTime = viewHolder.dbReport.getMonthTimeSpentSum(timestampFromDate, timestampToDate);

                if (cursorTime != null && cursorTime.getCount() > 0) {
                    if (cursorTime.moveToFirst()) {
                        do {
                            timeSpentSum = cursorTime.getString(0);
                        } while (cursorTime.moveToNext());
                    }
                }

                Cursor cursorAsana = viewHolder.dbReport.getMonthDistinctAsana(fromDate, toDate);
                if (cursorTime != null && cursorTime.getCount() > 0) {
                    if (cursorTime.moveToFirst()) {
                        do {
                            totalExc = cursorAsana.getString(0);
                        } while (cursorTime.moveToNext());
                    }
                }
//            }

            if(timeSpentSum != null){

                long toDays = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
                long toHours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeSpentSum)));
                long toMinutes = TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
                long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(timeSpentSum)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)));

//                if(toDays > 0) {
//                    totalDuration.setText(twoDigitString(toDays) + "d " + twoDigitString(toHours) + "h " + twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");
//                }
//                else if(toHours > 0) {
//                    totalDuration.setText(twoDigitString(toHours) + "h " + twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");
//                }
//                else if(toMinutes > 0){
//                    totalDuration.setText(twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");
//                }
//                else if(toSeconds > 0){
//                    viewHolder.date.setText("Ends in " + twoDigitString(toSeconds) + "s");
//                }

                totalDuration.setText(twoDigitString(toDays) + "d " +twoDigitString(toHours) + "h " + twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");

//            if (toHours > 0) {
//                totalDuration.setText(toHours + "h " + toMinutes + "m " + toSeconds + "s");
//            } else {
//                totalDuration.setText(toMinutes + "m " + toSeconds + "s");
//            }

                Log.d("TImeSpent", toHours + "h " + toMinutes + "m " + toSeconds + "s");
            }
            else{
                totalDuration.setText("00h 00m 00s");
            }

            if(totalExc != null) {
                totalExercise.setText(totalExc);
            }
            else{
                totalExercise.setText("00");
            }

//            if(!timeSpentSum.equalsIgnoreCase(null) || timeSpentSum.equalsIgnoreCase("00h 00m 00s")) {
//
//                long toHours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeSpentSum)));
//                long toMinutes = TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
//                long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(timeSpentSum)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)));
//
////            if (toHours > 0) {
//                totalDuration.setText(toHours + "h " + toMinutes + "m " + toSeconds + "s");
////            } else {
////                totalDuration.setText(toMinutes + "m " + toSeconds + "s");
////            }
//
//                Log.d("TImeSpent", toHours + "h " + toMinutes + "m " + toSeconds + "s");
//            }
//            else{
//                totalDuration.setText(timeSpentSum);
//            }

            dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogBuilder.show();


        });

//        animate(viewHolder);
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

//    private void animate(RecyclerView.ViewHolder viewHolder) {
//        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.report_list);
//        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
//    }

    @Override
    public int getItemCount() {
        return diseaseData.size();
    }

    public void setFilter(List<DiseaseData> datas1) {
        diseaseData = new ArrayList<>();
        diseaseData.addAll(datas1);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextViewSemiBold disease;
        private LinearLayout linearLayout;
        SQLiteHandlerReport dbReport;

        private ViewHolder(View view) {
            super(view);

            disease = view.findViewById(R.id.disease);
            linearLayout = view.findViewById(R.id.linearLayout);

            dbReport = new SQLiteHandlerReport(context);
        }
    }

}
