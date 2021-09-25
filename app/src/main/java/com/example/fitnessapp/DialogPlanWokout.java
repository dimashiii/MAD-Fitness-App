package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DialogPlanWokout extends AppCompatActivity {

    EditText enterEPlan;
    EditText enterEPlanDuration;
    Button btnBack;
    Button btnSavePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_plan_wokout);

        enterEPlan = (EditText) findViewById(R.id.enterPlan);
        enterEPlanDuration = (EditText) findViewById(R.id.enterPlanDuration);
        btnBack = (Button) findViewById(R.id.btnContinue);
        btnSavePlan = (Button) findViewById(R.id.btnSavePlan);

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("WorkoutPlanModel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WorkoutPlanModel workoutPlanModel = snapshot.getValue(WorkoutPlanModel.class);



                    String planName = workoutPlanModel.getPlanName();
                    String planDuration = workoutPlanModel.getPlanDuration();
                    enterEPlan.setText(planName);
                    enterEPlanDuration.setText(planDuration);
                    enterEPlan.setText(String.valueOf(postSnapshot.child("planName").getValue()));
                    enterEPlanDuration.setText(String.valueOf(postSnapshot.child("planDuration").getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSavePlan.setOnClickListener(view -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("planName", enterEPlan.getText().toString());
            map.put("planDuration", enterEPlanDuration.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("WorkoutPlanModel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                enterEPlan.setText("");
                enterEPlanDuration.setText("");
                Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Updated !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        btnBack.setOnClickListener(vew->{
            Intent intent = new Intent(this, Workout.class);


            String planName = enterEPlan.getText().toString();
            String planDuration = enterEPlanDuration.getText().toString();

            intent.putExtra("planName",planName);
            intent.putExtra("planDuration",planDuration);


            startActivity(intent);
        });
    }
}