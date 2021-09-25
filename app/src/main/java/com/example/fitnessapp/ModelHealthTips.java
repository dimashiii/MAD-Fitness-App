package com.example.fitnessapp;

public class ModelHealthTips
{
    String htopic,hdesc,hdate,purl;
    ModelHealthTips()
    {

    }
    public ModelHealthTips(String htopic, String hdesc, String hdate, String purl) {
        this.htopic = htopic;
        this.hdesc = hdesc;
        this.hdate = hdate;
        this.purl = purl;
    }

    public String getHtopic() {
        return htopic;
    }

    public void setHtopic(String htopic) {
        this.htopic = htopic;
    }

    public String getHdesc() {
        return hdesc;
    }

    public void setHdesc(String hdesc) {
        this.hdesc = hdesc;
    }

    public String getHdate() {
        return hdate;
    }

    public void setHdate(String hdate) {
        this.hdate = hdate;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
