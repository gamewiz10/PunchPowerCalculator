package com.example.testcv;

public class PowerData {
    private static PowerData instance;
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

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
