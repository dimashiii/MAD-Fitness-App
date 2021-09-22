package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateWorkoutPlan extends AppCompatActivity {

    EditText enterPlan;
    EditText enterPlanDuration;
    Button btnContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout_plan);

        enterPlan = findViewById(R.id.enterPlan);
        enterPlanDuration = findViewById(R.id.enterPlanDuration);
        btnContinue = findViewById(R.id.btnContinue);

        /* btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Workout();

            }
        });*/

    }

    //Navigation part-->workout
    public void onClick(View view) {
        Intent intent1 = new Intent(this,Workout.class);
        Intent intent2 = new Intent(this,PlanWorkout.class);

        String planName = enterPlan.getText().toString();
        String planDuration = enterPlanDuration.getText().toString();

       // intent.putExtra("PLAN_NAME",planName);
        //intent.putExtra("PLAN_DURATION",planDuration);
        Bundle extras = new Bundle();
        extras.putString("PLAN_NAME",planName);
        extras.putString("PLAN_DURATION",planDuration);

        intent1.putExtras(extras);
        intent2.putExtras(extras);

        startActivity(intent1);

    }
}