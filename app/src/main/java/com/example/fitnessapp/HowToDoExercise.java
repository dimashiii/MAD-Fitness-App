package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.adapter.ExerciseAdapter;
import com.example.fitnessapp.model.ExercisesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HowToDoExercise extends AppCompatActivity {

    TextView eName;
    TextView eCount;
    TextView eDes;
    Button edit;
    Button delete;

    String name, count, des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_do_exercise);

        Intent intent = getIntent();

        eName = (TextView)findViewById(R.id.eName);
        eCount = (TextView)findViewById(R.id.eCount);
        eDes = (TextView)findViewById(R.id.eDescription);
        edit = (Button)findViewById(R.id.eeditbtn);
        delete = (Button)findViewById(R.id.eeditbtn);


        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("ExercisesModel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ExercisesModel exercisesModel = snapshot.getValue(ExercisesModel.class);

                    String name = exercisesModel.getExerciseName();
                    String count = String.valueOf(exercisesModel.getCount());
                    String des = exercisesModel.getDuration();


                    eName.setText(String.valueOf(postSnapshot.child("enterExerciseName").getValue()));
                    eCount.setText(String.valueOf(postSnapshot.child("enterCount").getValue()));
                    eDes.setText(String.valueOf(postSnapshot.child("enterExerciseDescription").getValue()));



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter();
        delete.setOnClickListener(view -> {
            ExercisesModel exercisesModel = new ExercisesModel();
            exerciseAdapter.remove("exerciseDuartion").addOnSuccessListener(success -> {
               eDes.setText(" ");
               Toast.makeText(this, "Details Successfully deleted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(failure -> {
                Toast.makeText(this, "Not deleted Succesfully", Toast.LENGTH_SHORT).show();
            });
        });


    }
}