package com.example.fitnessapp.model;

public class FastingBeginnerModel {
    String date, time, repeat, repeatNo, repeatType,title;

    public FastingBeginnerModel() {
    }

    public FastingBeginnerModel(String date, String time, String repeat, String repeatNo, String repeatType, String title) {
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.repeatNo = repeatNo;
        this.repeatType = repeatType;
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

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
