package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.adapter.ExerciseAdapter;
import com.example.fitnessapp.model.ExercisesModel;
import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlanWorkout extends AppCompatActivity {

    TextView textexercisename;
    TextView textduration;
    TextView count;
    ImageView img;

    Button editbtn;
    Button deletebtn;
    Button viewmorebtn;
    Button playbtn;


    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_workout);

        Intent intent = getIntent();

        textexercisename = findViewById(R.id.textexercisename1);
        textduration = findViewById(R.id.textexerciseduration);
        count = findViewById(R.id.count);
        img = findViewById(R.id.plank);
        editbtn = findViewById(R.id.editbtn2);
        deletebtn = findViewById(R.id.deletebtn);
        viewmorebtn = findViewById(R.id.viewMore);

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("ExercisesModel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ExercisesModel exercisesModel = snapshot.getValue(ExercisesModel.class);

                    String exerciseName = exercisesModel.getExerciseName();
                    String exerciseDuration = exercisesModel.getDuration();
                    String exerciseCount = String.valueOf(exercisesModel.getCount());

                    textexercisename.setText(exerciseName);
                    textduration.setText(exerciseDuration);
                    count.setText(exerciseCount);

                    textexercisename.setText(String.valueOf(postSnapshot.child("enterExerciseName").getValue()));
                    textduration.setText(String.valueOf(postSnapshot.child("enterDuration").getValue()));
                    count.setText(String.valueOf(postSnapshot.child("enterCount").getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter();
        deletebtn.setOnClickListener(view -> {
            ExercisesModel exercisesModel = new ExercisesModel();
            exerciseAdapter.remove("exerciseName").addOnSuccessListener(success -> {
                textexercisename.setText(" ");
                textduration.setText(" ");
                count.setText(" ");
                Toast.makeText(this, "Details Successfully deleted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(failure -> {
                Toast.makeText(this, "Not deleted Succesfully", Toast.LENGTH_SHORT).show();
            });
        });

        viewmorebtn.setOnClickListener(view->{
            Intent intentNext = new Intent(this, HowToDoExercise.class);
            startActivity(intentNext);
        });


    }
}