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
import com.example.fitnessapp.model.diarymodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Progress extends AppCompatActivity {

    TextView textDate;
    TextView textName;
    TextView textCurrentW;
    TextView textBminow;
    TextView textDesireW;
    Button btneditD;
    Button btndeleteD;
    Button btnaddD;



    Intent intentNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        //get values from create progress diary

        Intent intent = getIntent();


        textName = (TextView) findViewById(R.id.dnamevalue);
        textDate = (TextView) findViewById(R.id.datevalueD);
        textCurrentW = (TextView) findViewById(R.id.dcurrentWvalue);
        textBminow = (TextView) findViewById(R.id.dbmivalue);
        textDesireW = (TextView) findViewById(R.id.ddesireWvalue);

        btnaddD = (Button) findViewById(R.id.adddiaryrecBtn);
        btneditD = (Button) findViewById(R.id.weditbtn);
        btndeleteD = (Button) findViewById(R.id.wdeletebtn);





        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("diarymodel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    diarymodel diarymodel = snapshot.getValue(diarymodel.class);




                    String dname = diarymodel.getDname();
                    String ddate = diarymodel.getDdate();
                    String dcurrentweight = diarymodel.getDcurrentweight();
                    String dbmi = diarymodel.getDbmi();
                    String ddesiredweight = diarymodel.getDdesiredweight();


                    textName.setText(dname);
                    textDate.setText(ddate);
                    textCurrentW.setText(dcurrentweight);
                    textBminow.setText(dbmi);
                    textDesireW.setText(ddesiredweight);


                    textName.setText(String.valueOf(postSnapshot.child("dname").getValue()));
                    textDate.setText(String.valueOf(postSnapshot.child("ddate").getValue()));
                    textCurrentW.setText(String.valueOf(postSnapshot.child("dcurrentweight").getValue()));
                    textBminow.setText(String.valueOf(postSnapshot.child("dbmi").getValue()));
                    textDesireW.setText(String.valueOf(postSnapshot.child("ddesiredweight").getValue()));


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btneditD.setOnClickListener(view->{
            Intent intentEdit = new Intent(this, UpdateBmiDiaryForm.class);
            startActivity(intentEdit);
        });

        btndeleteD.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("diarymodel").removeValue().addOnSuccessListener(success -> {
                textName.setText(" ");
                textDate.setText(" ");
                textCurrentW.setText(" ");
                textBminow.setText(" ");
                textDesireW.setText(" ");

                Toast.makeText(this, "Details Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not deleted Successfully!", Toast.LENGTH_SHORT).show();
            });





        });

        btnaddD.setOnClickListener(next -> {
            Intent intentNext = new Intent(this, BmiDiaryForm.class);
            startActivity(intentNext);
        });


    }
}