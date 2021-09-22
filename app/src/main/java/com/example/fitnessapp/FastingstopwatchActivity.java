package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.databinding.ActivityFastingstopwatchBinding;
import com.example.fitnessapp.model.TimerModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class FastingstopwatchActivity extends AppCompatActivity {
    ActivityFastingstopwatchBinding binding;
    String time;
    boolean is_running = true;
    DatabaseReference myRef;
    int sec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFastingstopwatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        String uniqueID = pref.getString("uuid", "");

        Intent intent = getIntent();
        int activity = intent.getIntExtra("activity", -1);
        String key = intent.getStringExtra("key");


        myRef = FirebaseDatabase.getInstance().getReference().child("fastingTime").child(uniqueID).child(key);

        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("sec");
        }
        binding.startNow.setOnClickListener(v -> {
            is_running = true;
            running_Timer();
        });
        binding.finishFasting.setOnClickListener(v -> {
            is_running = false;
            time = getTimeNow();
            TimerModel model = new TimerModel(time, String.valueOf(sec));
            myRef.setValue(model, (error, ref) -> {
                if (error != null) {
                    Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", sec);

    }

    private String getTimeNow() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        time = hour + ":" + minutes + ":" + seconds;
        return time;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

    }

    private void running_Timer() {

        final Handler handle = new Handler();

        handle.post(new Runnable() {
            @Override

            public void run() {


                int hrs = sec / 3600;
                int mins = (sec % 3600) / 60;
                int secs = sec % 60;
                String time_t = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, mins, secs);
                binding.timer.setText(time_t);
                Log.d("timeee", time_t);
                if (is_running) {
                    sec++;
                }
                handle.postDelayed(this, 1000);


            }
        });
    }

}