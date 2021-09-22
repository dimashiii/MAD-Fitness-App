package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    TextView txtPlanName;
    TextView txtPlanDuration;
    Button btnedit;
    Button btndelete;

    String planName;
    String planDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        txtPlanName = findViewById(R.id.txtPlanName);
        txtPlanDuration = findViewById(R.id.txtPlanDuration);
        btnedit=(Button)findViewById(R.id.editbtn);
        btndelete= (Button) findViewById(R.id.deletebtn);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //get values from create workout plan
        Intent intent = getIntent();

        //planName = intent.getStringExtra("PLAN_NAME");
        //planDuration = intent.getStringExtra("PLAN_DURATION");
        Bundle extras = intent.getExtras();
        planName = extras.getString("PLAN_NAME");
        planDuration = extras.getString("PLAN_DURATION");

        txtPlanName.setText(planName);
        txtPlanDuration.setText(planDuration);
    }


}