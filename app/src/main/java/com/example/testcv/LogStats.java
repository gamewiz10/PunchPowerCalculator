package com.example.testcv;

public class LogStats {
    private int weight;
    private String date;
    private String time;
    private int energy;

    public LogStats(){

    }

    public LogStats(int weight, String date, String time, int energy){
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

    public int getEnergy(){
        return energy;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }
}
