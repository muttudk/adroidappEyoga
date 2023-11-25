package com.ntpl.eyoga.adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;
import com.ntpl.eyoga.helper.SQLiteHandlerReport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeeklyWeekDataAdapter extends RecyclerView.Adapter<WeeklyWeekDataAdapter.ViewHolder>  {

    private List<DiseaseData> diseaseData;
    private Context context;
    private String monthNo;

    public WeeklyWeekDataAdapter(Context context,List<DiseaseData> diseaseData, String monthNo) {
        this.diseaseData = diseaseData;
        this.context = context;
        this.monthNo = monthNo;
    }

    @Override
    public WeeklyWeekDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_disease, viewGroup, false);
        return new WeeklyWeekDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WeeklyWeekDataAdapter.ViewHolder viewHolder, int i) {
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
            String timeSpentSum = "00h 00m 00s", totalExc = "00", fromDate = "",toDate = "";

            if(data.getMonth().equalsIgnoreCase("01")) {
                fromDate = "2021-" + monthNo + "-01";
                toDate = "2021-" + monthNo + "-07";
            }
            else if(data.getMonth().equalsIgnoreCase("02")) {
                fromDate = "2021-" + monthNo + "-08";
                toDate = "2021-" + monthNo + "-14";
            }
            else if(data.getMonth().equalsIgnoreCase("03")) {
                fromDate = "2021-" + monthNo + "-15";
                toDate = "2021-" + monthNo + "-21";
            }
            else if(data.getMonth().equalsIgnoreCase("04")) {
                fromDate = "2021-" + monthNo + "-22";
                toDate = "2021-" + monthNo + "-31";
            }

            Cursor cursorTime = viewHolder.dbReport.getMonthTimeSpentSum(fromDate, toDate);

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

            if(timeSpentSum != null){

                long toDays = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
                long toHours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeSpentSum)));
                long toMinutes = TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(Long.parseLong(timeSpentSum)));
                long toSeconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(timeSpentSum)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(timeSpentSum)));

                totalDuration.setText(twoDigitString(toDays) + "d " +twoDigitString(toHours) + "h " + twoDigitString(toMinutes) + "m " + twoDigitString(toSeconds) + "s");

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
