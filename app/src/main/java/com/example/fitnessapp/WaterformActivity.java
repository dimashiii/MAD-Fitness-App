package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.fitnessapp.databinding.ActivityWaterformBinding;
import com.example.fitnessapp.model.WaterBeginnerModel;
import com.example.fitnessapp.utils.DatePickerFragment;
import com.example.fitnessapp.utils.NumberPickerDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class WaterformActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, NumberPicker.OnValueChangeListener {
    ActivityWaterformBinding binding;
    DatabaseReference myRef;
    int repeat = 0;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaterformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        String uniqueID = pref.getString("uuid", "");

        Intent intent = getIntent();
        int activity = intent.getIntExtra("activity", -1);
        String key = intent.getStringExtra("key");
        myRef = FirebaseDatabase.getInstance().getReference().child("waterConsumptionPlan").child(uniqueID);
        Bundle args = new Bundle();

        binding.waterDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
        binding.waterTime.setOnClickListener(v -> {

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> {


                String format = getTimeFormat(selectedHour);

                binding.waterTimeValue.setText(String.format("%02d:%02d %s", selectedHour,
                        selectedMinute, format));
            }, 12, 0, false);
            mTimePicker.show();

        });
        binding.waterRepeat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                repeat = 1;
            } else {
                repeat = 0;
            }

        });
        binding.waterRepeatNo.setOnClickListener(v -> {
            NumberPickerDialog newFragment = new NumberPickerDialog();
            args.clear();
            args.putBoolean("repeatType", false);
            newFragment.setArguments(args);
            newFragment.setValueChangeListener(this);
            newFragment.show(getSupportFragmentManager(), "number picker");
        });
        binding.waterDaily.setOnClickListener(v -> {
            NumberPickerDialog newFragment = new NumberPickerDialog();
            args.putBoolean("waterDaily", true);
            newFragment.setArguments(args);
            newFragment.setValueChangeListener(this);
            newFragment.show(getSupportFragmentManager(), "number picker");
        });

        binding.save.setOnClickListener(v -> {

            WaterBeginnerModel model = new WaterBeginnerModel(binding.waterDateValue.getText().toString(), binding.waterTimeValue.getText().toString(),
                    String.valueOf(repeat), binding.waterRepeatNoValue.getText().toString(), binding.waterDailyValue.getText().toString(), binding.planTitle.getText().toString().trim());

            if (activity == 1) {
                myRef.child(key);
                myRef.setValue(model, (error, ref) -> {
                    if (error != null) {
                        Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {

                myRef.push().setValue(model, (error, ref) -> {
                    if (error != null) {
                        Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        binding.cancel.setOnClickListener(v -> finish());
        if (!TextUtils.isEmpty(key)) {
            getData(key, uniqueID);
        } else {
            getTimeNow();
        }


    }

    private String getTimeFormat(int selectedHour) {
        String timeFormat;
        if (selectedHour == 0) {
            selectedHour += 12;
            timeFormat = "AM";
        } else if (selectedHour == 12) {
            timeFormat = "PM";
        } else if (selectedHour > 12) {
            selectedHour -= 12;
            timeFormat = "PM";
        } else {
            timeFormat = "AM";
        }
        return timeFormat;
    }

    private void getTimeNow() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        String format = getTimeFormat(hour);
        binding.waterTimeValue.setText(c.get(Calendar.HOUR) + ":" + minutes  + " "+ format);
        binding.waterDateValue.setText(c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH));

    }

    private void getData(String key, String uniqueID) {
        myRef = FirebaseDatabase.getInstance().getReference().child("waterConsumptionPlan").child(uniqueID).child(key);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WaterBeginnerModel model = dataSnapshot.getValue(WaterBeginnerModel.class);
                if (dataSnapshot.exists()) {
                    binding.planTitle.setText(model.getTitle());
                    binding.waterDateValue.setText(model.getDate());
                    binding.waterTimeValue.setText(model.getTime());
                    binding.waterRepeatNoValue.setText(model.getRepeatNo());
                    binding.waterDailyValue.setText(model.getDailyWater());
                    if (model.getRepeat().equals("1")) {
                        binding.waterRepeat.setChecked(true);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        binding.waterDateValue.setText(year + "/" + month + "/" + dayOfMonth);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int repeatType) {
        if (repeatType == 1) {

            binding.waterDailyValue.setText(picker.getDisplayedValues()[picker.getValue() - 1]);
            return;
        }
        binding.waterRepeatNoValue.setText(picker.getDisplayedValues()[picker.getValue() - 1]);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}