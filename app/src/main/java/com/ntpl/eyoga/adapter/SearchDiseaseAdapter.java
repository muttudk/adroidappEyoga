package com.ntpl.eyoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.TextViewMedium;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SearchDiseaseAdapter extends ArrayAdapter {

    Context context;
    List<String> Broadcast_message;
    List<String>Duplicate_list;
    List<String>Suggestions;

    public SearchDiseaseAdapter(Context context, List<String> Broadcast_message) {
        super(context, R.layout.autocomplete_row);
        this.context=context;
        this.Broadcast_message = Broadcast_message;
        Duplicate_list = new ArrayList<>();
        Duplicate_list.addAll(Broadcast_message);
        this.Suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return Broadcast_message.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return Broadcast_message.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            LayoutInflater inflater;
            inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.autocomplete_row,null);
        }
        TextViewMedium item = convertView.findViewById(R.id.item);
        item.setText(Broadcast_message.get(position));
        return convertView;
    }

    @Override
    public Filter getFilter(){
        Filter filter = new Filter(){

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                String str = resultValue.toString();
                return str;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint!=null){
                    Suggestions.clear();
                    for (String s : Duplicate_list){
                        if (s.toLowerCase().contains(constraint.toString().toLowerCase())){
                            Suggestions.add(s);
                        }
                    }

                    FilterResults result = new FilterResults();
                    result.values=Suggestions;
                    result.count=Suggestions.size();
                    return result;
                }else{
                    return new FilterResults();
                }

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<String>Filtered_list = (ArrayList<String>) results.values;
                if ((results!=null)&&(results.count>0)){
                    Broadcast_message.clear();
                    for (String s:Filtered_list){
                        Broadcast_message.add(s);
                    }
                    notifyDataSetChanged();
                }else if (constraint == null) {
                    // no filter, add entire original list back in
                    Broadcast_message.clear();
                    Broadcast_message.addAll(Duplicate_list);
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}

