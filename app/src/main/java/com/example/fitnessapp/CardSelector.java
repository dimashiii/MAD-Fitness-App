package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class CardSelector extends AppCompatActivity {

    CardView workouts;
    CardView dailygoals;
    CardView mealplan;
    CardView health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selector);

        dailygoals = (CardView)findViewById(R.id.dailygoals);
        mealplan = (CardView)findViewById(R.id.mealPlan);
        health = (CardView)findViewById(R.id.health);
        workouts = (CardView)findViewById(R.id.workout);

        //dailygoal
        dailygoals.setOnClickListener(view->{
            Intent intentgoals = new Intent(this, GoalsoverviewActivity.class);

            startActivity(intentgoals);
        });

        //mealPlan--> thissa
        mealplan.setOnClickListener(view->{
            Intent intentmeals = new Intent(this, AddMeals.class);

            startActivity(intentmeals);
        });

        //health

        health.setOnClickListener(view->{
            Intent intenthealth = new Intent(this, HealthJournal.class);

            startActivity(intenthealth);
        });

        //workout
        workouts.setOnClickListener(view->{
            Intent intentworkouts = new Intent(this, CreateWorkoutPlan.class);

            startActivity(intentworkouts);
        });
    }


}