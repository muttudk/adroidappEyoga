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

import androidx.recyclerview.widget.RecyclerView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.activities.WeeklyWeekReportActivity;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;
import com.ntpl.eyoga.helper.SQLiteHandlerReport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeeklyDataAdapter extends RecyclerView.Adapter<WeeklyDataAdapter.ViewHolder>  {

    private List<DiseaseData> diseaseData;
    private Context context;

    public WeeklyDataAdapter(Context context,List<DiseaseData> diseaseData) {
        this.diseaseData = diseaseData;
        this.context = context;
    }

    @Override
    public WeeklyDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_disease, viewGroup, false);
        return new WeeklyDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WeeklyDataAdapter.ViewHolder viewHolder, int i) {
        final DiseaseData data = diseaseData.get(i);

        viewHolder.disease.setText(data.getDisease());

        viewHolder.linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, WeeklyWeekReportActivity.class);
            intent.putExtra("month",data.getDisease());
            intent.putExtra("monthNo", data.getMonth());
            context.startActivity(intent);
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
