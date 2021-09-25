package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateWorkoutPlan extends AppCompatActivity {

    EditText enterPlan;
    EditText enterPlanDuration;
    Button btnContinue;
    Button btnSavePlan;

    DatabaseReference dbRef;
    WorkoutPlanModel plan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout_plan);

        enterPlan = (EditText) findViewById(R.id.enterPlan);
        enterPlanDuration = (EditText) findViewById(R.id.enterPlanDuration);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnSavePlan = (Button) findViewById(R.id.btnSavePlan);


        btnSavePlan.setOnClickListener(view -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("planName", enterPlan.getText().toString());
            map.put("planDuration", enterPlanDuration.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("WorkoutPlanModel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                enterPlan.setText("");
                enterPlanDuration.setText("");
                Toast.makeText(this, "Details Inserted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Inserted !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        btnContinue.setOnClickListener(view -> {
            Intent intent = new Intent(this, Workout.class);


            String planName = enterPlan.getText().toString();
            String planDuration = enterPlanDuration.getText().toString();

            intent.putExtra("planName",planName);
            intent.putExtra("planDuration",planDuration);


            startActivity(intent);
        });




    }
}