package com.example.testcv;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LogData {
    private static LogData instance;
    private List<LogStats> data = new ArrayList<>();
    private OnDataChangedListener listener;
    private LogData(){
//        private constructor
    }
    public static LogData getInstance() {
        if (instance == null){
            instance = new LogData();
        }
        return instance;
    }
    public List<LogStats> getData() {
        return data;
    }

    public void addLogStats(LogStats logStats){
        data.add(logStats);
        notifyDataChanged();
    }
    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.listener = listener;
    }
    private void notifyDataChanged() {
        if (listener != null) {
            listener.onDataChanged();
        }
    }

    public interface OnDataChangedListener {
        void onDataChanged();
    }


}
