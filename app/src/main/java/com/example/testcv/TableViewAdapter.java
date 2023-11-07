package com.example.testcv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter {
    List<LogStats> statsList;

    public TableViewAdapter (List<LogStats> statsList){
        this.statsList = statsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_items, parent, false);
        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;
        int rowPos = rowViewHolder.getAdapterPosition();

        if(rowPos == 0){
            rowViewHolder.txtweight.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtdate.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txttime.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtenergy.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtweight.setText("weight (lbs)");
            rowViewHolder.txtdate.setText("date");
            rowViewHolder.txttime.setText("time");
            rowViewHolder.txtenergy.setText("energy");
        }
        else {
            LogStats stats = statsList.get(rowPos -1);

            rowViewHolder.txtweight.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtdate.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txttime.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtenergy.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtweight.setText(stats.getWeight()+"");
            rowViewHolder.txtdate.setText(stats.getDate()+"");
            rowViewHolder.txttime.setText(stats.getTime()+"");
            rowViewHolder.txtenergy.setText(stats.getEnergy()+"");
        }
    }

    @Override
    public int getItemCount() {
        return statsList.size()+1;
    }
    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtweight;
        protected TextView txtdate;
        protected TextView txttime;
        protected TextView txtenergy;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtweight = itemView.findViewById(R.id.weight);
            txtdate = itemView.findViewById(R.id.date);
            txttime = itemView.findViewById(R.id.time);
            txtenergy = itemView.findViewById(R.id.energy);
        }
    }
}
