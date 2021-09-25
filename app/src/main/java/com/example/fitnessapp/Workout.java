package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Workout extends AppCompatActivity {

    TextView txtPlanName;
    TextView txtPlanDuration;
    Button btnedit;
    Button btndelete;
    Button btnNext;



    Intent intentNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //get values from create workout plan

        Intent intent = getIntent();

        //planName = intent.getStringExtra("planName");
        //planDuration = intent.getStringExtra("planDuration");



        txtPlanName = (TextView) findViewById(R.id.txtPlanName);
        txtPlanDuration = (TextView) findViewById(R.id.txtPlanDuration);
        btnedit = (Button) findViewById(R.id.editbtn);
        btndelete = (Button) findViewById(R.id.deletebtn);
        btnNext = (Button) findViewById(R.id.btnnNext);


        //txtPlanName.setText(planName);
        //txtPlanDuration.setText(planDuration);

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("WorkoutPlanModel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WorkoutPlanModel workoutPlanModel = snapshot.getValue(WorkoutPlanModel.class);



                    String planName = workoutPlanModel.getPlanName();
                    String planDuration = workoutPlanModel.getPlanDuration();
                    txtPlanName.setText(planName);
                    txtPlanDuration.setText(planDuration);
                    txtPlanDuration.setText(String.valueOf(postSnapshot.child("planDuration").getValue()));
                    txtPlanName.setText(String.valueOf(postSnapshot.child("planName").getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btndelete.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("WorkoutPlanModel").removeValue().addOnSuccessListener(success -> {
                txtPlanName.setText(" ");
                txtPlanDuration.setText(" ");
                Toast.makeText(this, "Details Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not deleted Successfully!", Toast.LENGTH_SHORT).show();
            });


            btnNext.setOnClickListener(next -> {
                Intent intentNext = new Intent(this, AddExercise.class);
                startActivity(intentNext);
            });


        });


    }
}