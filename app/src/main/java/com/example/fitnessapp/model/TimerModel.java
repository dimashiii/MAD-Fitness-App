package com.example.fitnessapp.model;

public class TimerModel {
    String dateTime,fastingTime;

    public TimerModel() {
    }

    public TimerModel(String dateTime, String fastingTime) {
        this.dateTime = dateTime;
        this.fastingTime = fastingTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFastingTime() {
        return fastingTime;
    }

    public void setFastingTime(String fastingTime) {
        this.fastingTime = fastingTime;
    }
}
