package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model.diarymodel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateBmiDiaryForm extends AppCompatActivity {

    EditText enterDname;
    EditText enterDdate;
    EditText enterDcurrentweight;
    EditText enterDbmi;
    EditText enterDdesiredweight;
    Button btnSave1;
    Button btnAdd1;
    Button btnBack1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_diary_form);

        enterDname= findViewById(R.id.add_dname);
        enterDdate= findViewById(R.id.add_ddate);
        enterDcurrentweight= findViewById(R.id.add_currentweight);
        enterDbmi= findViewById(R.id.add_bmi);
        enterDdesiredweight= findViewById(R.id.add_desiredweight);
        btnSave1 = findViewById(R.id.diarysubmit);
        //btnAdd1 = findViewById(R.id.diaryadd);
        btnBack1 = findViewById(R.id.diaryback);


        diarymodel dmodel = new diarymodel();


        btnSave1.setOnClickListener(view->{
            HashMap<String, Object> map = new HashMap<>();
            map.put("enterDname", enterDname.getText().toString());
            map.put("enterDdate", enterDdate.getText().toString());
            map.put("enterDcurrentweight", enterDcurrentweight.getText().toString());
            map.put("enterDbmi",enterDbmi.getText().toString());
            map.put("enterDdesiredweight", enterDdesiredweight.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("diarymodel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                enterDname.setText("");
                enterDdate.setText("");
                enterDcurrentweight.setText("");
                enterDbmi.setText("");
                enterDdesiredweight.setText("");
                Toast.makeText(this, "Details Updated Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Updated !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        btnBack1.setOnClickListener(view->{
            Intent intent = new Intent(this,PlanWorkout.class);

            String dname = enterDname.getText().toString();
            String ddate = enterDdate.getText().toString();
            String dcurrentweight = enterDcurrentweight.getText().toString();
            String dbmi = enterDbmi.getText().toString();
            String ddesiredweight = enterDdesiredweight.getText().toString();


            intent.putExtra("dname",dname);
            intent.putExtra("ddate ",ddate );
            intent.putExtra("dcurrentweight",dcurrentweight);
            intent.putExtra("dbmi",dbmi);
            intent.putExtra("desiredweight",ddesiredweight);

            startActivity(intent);
        });

        btnBack1.setOnClickListener(view->{
            Intent i= new Intent(this,Progress.class);
            startActivity(i);
        });


    }

}