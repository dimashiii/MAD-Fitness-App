package com.example.fitnessapp.model;

public class WorkoutPlanModel {

    String planName;
    String planDuration;

    public WorkoutPlanModel(){

    }
    public WorkoutPlanModel(String planName,String planDuration) {
        this.planName = planName;
        this.planDuration = planDuration;
    }



    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setPlanDuration(String planDuration) {
        this.planDuration = planDuration;
    }

    public String getPlanName() {
        return planName;
    }

    public String getPlanDuration() {
        return planDuration;
    }
}

