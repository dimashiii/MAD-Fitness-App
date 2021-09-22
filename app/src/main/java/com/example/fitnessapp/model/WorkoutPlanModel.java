package com.example.fitnessapp.model;

public class WorkoutPlanModel {

    String planName, planDuration;

    public WorkoutPlanModel(){

    };

    public String getPlanName() {
        return planName;
    }

    public String getPlanDuration() {
        return planDuration;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setPlanDuration(String planDuration) {
        this.planDuration = planDuration;
    }
}
