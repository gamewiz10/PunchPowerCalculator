package com.example.testcv;

public class PowerData {
    private static PowerData instance;
    private String date;
    private String time;
    private double power;

    private PowerData() {
        // Private constructor to prevent instantiation
    }

    public static PowerData getInstance() {
        if (instance == null) {
            instance = new PowerData();
        }
        return instance;
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
}
