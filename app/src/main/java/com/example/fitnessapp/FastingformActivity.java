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

import com.example.fitnessapp.databinding.ActivityFastingformBinding;
import com.example.fitnessapp.model.FastingBeginnerModel;
import com.example.fitnessapp.utils.DatePickerFragment;
import com.example.fitnessapp.utils.NumberPickerDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class FastingformActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, NumberPicker.OnValueChangeListener {
    ActivityFastingformBinding binding;
    DatabaseReference myRef, myRef1;
    int repeat = 0;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFastingformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        String uniqueID = pref.getString("uuid", "");

        Intent intent = getIntent();
        int activity = intent.getIntExtra("activity", -1);
        String key = intent.getStringExtra("key");


        myRef1 = FirebaseDatabase.getInstance().getReference("fastingPlans").child(uniqueID);
        Bundle args = new Bundle();

        binding.fastingDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
        binding.fastingTime.setOnClickListener(v -> {

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> {

                String format;

                if (selectedHour == 0) {
                    selectedHour += 12;
                    format = "AM";
                } else if (selectedHour == 12) {
                    format = "PM";
                } else if (selectedHour > 12) {
                    selectedHour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }
                binding.fastingTimeValue.setText(String.format("%02d:%02d %s", selectedHour,
                        selectedMinute, format));
            }, 12, 0, false);
            mTimePicker.show();

        });
        binding.fastingRepeat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                repeat = 1;
            } else {
                repeat = 0;
            }

        });
        binding.fastingRepeatNo.setOnClickListener(v -> {
            NumberPickerDialog newFragment = new NumberPickerDialog();
            args.putBoolean("repeatType", false);
            newFragment.setArguments(args);
            newFragment.setValueChangeListener(this);
            newFragment.show(getSupportFragmentManager(), "number picker");
        });
        binding.fastingRepeatType.setOnClickListener(v -> {
            NumberPickerDialog newFragment = new NumberPickerDialog();
            args.putBoolean("repeatType", true);
            newFragment.setArguments(args);
            newFragment.setValueChangeListener(this);
            newFragment.show(getSupportFragmentManager(), "number picker");
        });

        binding.save.setOnClickListener(v -> {
            FastingBeginnerModel model = new FastingBeginnerModel(binding.fastingDateValue.getText().toString(), binding.fastingTimeValue.getText().toString(),
                    String.valueOf(repeat), binding.fastingRepeatNoValue.getText().toString(), binding.fastingRepeatTypeValue.getText().toString(),
                    binding.fastingPlanTitle.getText().toString());
            if (activity == 1) {

                myRef.setValue(model, (error, ref) -> {
                    if (error != null) {
                        Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {

                myRef1.push().setValue(model, (error, ref) -> {
                    if (error != null) {
                        Toast.makeText(this, "Data could not be saved " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.cancel.setOnClickListener(v -> {
            finish();
        });
        if (!TextUtils.isEmpty(key)) {
            myRef = FirebaseDatabase.getInstance().getReference("fastingPlans").child(uniqueID).child(key);
            getData(key);

        } else {
            getTimeNow();
        }
    }

    private void getTimeNow() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        String format = getTimeFormat(hour);
        binding.fastingTimeValue.setText(c.get(Calendar.HOUR) + ":" + minutes + " " + format);
        binding.fastingDateValue.setText(c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH));

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


    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        binding.fastingDateValue.setText(year + "/" + month + "/" + dayOfMonth);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int repeatType) {

        if (repeatType == 1) {

            binding.fastingRepeatTypeValue.setText(picker.getDisplayedValues()[picker.getValue() - 1]);
            return;
        }
        binding.fastingRepeatNoValue.setText(picker.getDisplayedValues()[picker.getValue() - 1]);
    }

    private void getData(String key) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    FastingBeginnerModel model = dataSnapshot.getValue(FastingBeginnerModel.class);
                    binding.fastingPlanTitle.setText(model.getTitle());
                    binding.fastingDateValue.setText(model.getDate());
                    binding.fastingTimeValue.setText(model.getTime());
                    binding.fastingRepeatNoValue.setText(model.getRepeatNo());
                    binding.fastingRepeatTypeValue.setText(model.getRepeatType());
                    if (model.getRepeat().equals("1")) {
                        binding.fastingRepeat.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }


}
