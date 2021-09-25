package com.example.fitnessapp.model;

import android.widget.ImageView;

public class ExercisesModel {

    String exerciseName,exerciseDescription,gif,duration;
    Integer count;

    public ExercisesModel(){

    }

    public ExercisesModel(String exerciseName, String duration, Integer count, String exerciseDescription) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.count = count;
        this.exerciseDescription = exerciseDescription;
    }



    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
