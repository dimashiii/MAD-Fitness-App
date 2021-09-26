package com.example.fitnessapp.model;


public class diarymodel {
    public String dname, ddate, dcurrentweight, dbmi, ddesiredweight;

    public diarymodel() {
    }

    public diarymodel(String dname, String ddate, String dcurrentweight, String dbmi, String ddesiredweight) {
        this.dname = dname;
        this.ddate = ddate;
        this.dcurrentweight = dcurrentweight;
        this.dbmi = dbmi;
        this.ddesiredweight = ddesiredweight;

    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDcurrentweight() {
        return dcurrentweight;
    }

    public void setDcurrentweight(String dcurrentweight) {
        this.dcurrentweight = dcurrentweight;
    }

    public String getDbmi() {
        return dbmi;
    }

    public void setDbmi(String dbmi) {
        this.dbmi = dbmi;
    }

    public String getDdesiredweight() {
        return ddesiredweight;
    }

    public void setDdesiredweight(String ddesiredweight) {
        this.ddesiredweight = ddesiredweight;
    }
}

