package com.ntpl.eyoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.activities.DietPlannerDetailsActivity;
import com.ntpl.eyoga.activities.DiseaseDetailsActivity;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.DiseaseData;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDataAdapter extends RecyclerView.Adapter<DiseaseDataAdapter.ViewHolder>  {

    private List<DiseaseData> diseaseData;
    private final Context context;

    public DiseaseDataAdapter(Context context,List<DiseaseData> diseaseData) {
        this.diseaseData = diseaseData;
        this.context = context;
    }

    @Override
    public DiseaseDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_disease, viewGroup, false);
        return new DiseaseDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DiseaseDataAdapter.ViewHolder viewHolder, int i) {
        final DiseaseData data = diseaseData.get(i);

        viewHolder.disease.setText(data.getDisease());

        viewHolder.linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DietPlannerDetailsActivity.class);
            intent.putExtra("diseaseName",data.getDisease());
            context.startActivity(intent);
        });

//        animate(viewHolder);
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

        private ViewHolder(View view) {
            super(view);

            disease = view.findViewById(R.id.disease);
            linearLayout = view.findViewById(R.id.linearLayout);

        }
    }

}
