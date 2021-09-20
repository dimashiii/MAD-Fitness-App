package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.databinding.ActivityWaterdashboardBinding;
import com.example.fitnessapp.model.WaterConsumptionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class WaterdashboardActivity extends AppCompatActivity {

    ActivityWaterdashboardBinding binding;
    DatabaseReference myRef;
    String time;
    String uniqueID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaterdashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        uniqueID = pref.getString("uuid", "");

        Intent intent = getIntent();
        int activity = intent.getIntExtra("activity", -1);
        String key = intent.getStringExtra("key");
        String waterQty = intent.getStringExtra("waterQty");
        myRef = FirebaseDatabase.getInstance().getReference().child("waterConsumption").child(uniqueID).child(key);

        binding.op50ml.setOnClickListener(v -> updateTotal(50));

        binding.op100ml.setOnClickListener(v -> updateTotal(100));

        binding.op150ml.setOnClickListener(v -> updateTotal(150));

        binding.op200ml.setOnClickListener(v -> updateTotal(200));

        binding.op250ml.setOnClickListener(v -> updateTotal(250));

        binding.reset.setOnClickListener(v -> {
            binding.totalConsumed.setText("0");
            updateFirebase("0");
        });

        binding.opCustom.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_water_layout, null);
            builder.setView(dialogView);
            builder.setTitle("Custom Water Consumption");
            View view;
            EditText water = dialogView.findViewById(R.id.waterQty);
            builder.setPositiveButton("Ok", (dialog, which) -> {
                int customQty = Integer.parseInt(water.getText().toString().trim());
                updateTotal(customQty);
            }).setNegativeButton("Cancle", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        getData(waterQty);
    }

    private void getData(String waterQty) {

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    WaterConsumptionModel model = snapshot.getValue(WaterConsumptionModel.class);
                    binding.totalConsumed.setText(model.getWaterConsumption());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.waterDailyQty.setText("/" + waterQty);
    }

    private void updateTotal(int waterQty) {
        int subTotal = Integer.parseInt(binding.totalConsumed.getText().toString().trim());
        binding.totalConsumed.setText(String.valueOf(subTotal + waterQty));
        updateFirebase(binding.totalConsumed.getText().toString());
    }

    private void updateFirebase(String totalQty) {
        time = getTimeNow();
        WaterConsumptionModel model = new WaterConsumptionModel(time, totalQty);
        myRef.setValue(model, (error, ref) -> {
            if (error != null) {
                Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getTimeNow() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        time = hour + ":" + minutes + ":" + seconds;
        return time;
    }

}
