package com.example.testcv;

public class LogStats {
    private int weight;
    private String date;
    private String time;
    private String energy;



    public LogStats(int weight, String date, String time, String energy){
        this.weight = weight;
        this.date = date;
        this.time = time;
        this.energy = energy;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getEnergy(){
        return energy;
    }

    public void setEnergy(String energy){
        this.energy = energy;
    }
}
