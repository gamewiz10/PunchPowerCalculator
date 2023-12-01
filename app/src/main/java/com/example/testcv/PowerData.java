package com.example.testcv;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class PowerData {
    private static PowerData instance;
    private String date;
    private String time;
    private double power;
    int index;
    int weight;
    double BagWeight;
    private Context context;
    private List<LogStats> stats = new ArrayList<>();
    private double height;
    private TableViewAdapter singletonAdapter;

    private Logbook logbook;

    private PowerData() {
//        loadSavedData();
        // Private constructor to prevent instantiation
        singletonAdapter = new TableViewAdapter(stats);
    }

    public static PowerData getInstance() {
        if (instance == null) {
            instance = new PowerData();
        }
        return instance;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getCurrentDate() {
        return date;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setTime(String time){this.time = time;}
    public String getTime(){return time;}

    public int getWeight(){return weight;}

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBagWeight() {
        return BagWeight;
    }

    public void setBagWeight(double bagWeight) {
        BagWeight = bagWeight;
    }

    public List<LogStats> getStats() {
        return stats;
    }
    public void setStats(int weight, String date, String time, String power){
        LogStats logStats = new LogStats(weight, date, time, power);
        stats.add(logStats);
        singletonAdapter.notifyItemInserted(stats.size());
    }
    public void removeStats(){
        stats.remove(index);
        singletonAdapter.notifyItemRemoved(stats.size());
    }
    public TableViewAdapter getSingletonAdapter(){
        return singletonAdapter;
    }

    public void updateSingletonAdapter(List<LogStats> stats) {
        this.stats = stats;
//        stats.add(this.stats);
        singletonAdapter.notifyItemInserted(stats.size());
    }

//    all the shared preference stuff

    public void setContext(Context context) {
        this.context = context;
    }
    public void SaveData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String dataJson = toJson(stats);

        editor.putString("data", dataJson);
        editor.apply();
    }

    public void loadSavedData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedDataJson = sharedPreferences.getString("data", null);

        if (savedDataJson != null) {
            stats = fromJson(savedDataJson);
        }
    }

    private List<LogStats> fromJson(String json) {
        Gson gson = new Gson();

        java.lang.reflect.Type type = new TypeToken<List<LogStats>>(){}.getType();

        return gson.fromJson(json,type);
    }


    private String toJson(List<LogStats> data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }
}
