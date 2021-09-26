package com.example.fitnessapp.model;


public class diarymodel {
    public String date, time, repeat, repeatNo, dailyWater,title;

    public diarymodel() {
    }

    public diarymodel(String date, String time, String repeat, String repeatNo, String dailyWater, String title) {
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.repeatNo = repeatNo;
        this.dailyWater = dailyWater;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getRepeatNo() {
        return repeatNo;
    }

    public void setRepeatNo(String repeatNo) {
        this.repeatNo = repeatNo;
    }

    public String getDailyWater() {
        return dailyWater;
    }

    public void setDailyWater(String dailyWater) {
        this.dailyWater = dailyWater;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

