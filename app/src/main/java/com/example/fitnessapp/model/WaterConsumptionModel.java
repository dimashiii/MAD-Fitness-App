package com.example.fitnessapp.model;

public class WaterConsumptionModel {
    String date,waterConsumption;

    public WaterConsumptionModel() {
    }

    public WaterConsumptionModel(String date, String waterConsumption) {
        this.date = date;
        this.waterConsumption = waterConsumption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(String waterConsumption) {
        this.waterConsumption = waterConsumption;
    }
}
