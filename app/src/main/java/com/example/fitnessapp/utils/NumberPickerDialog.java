package com.example.fitnessapp.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class NumberPickerDialog extends DialogFragment {

    boolean repeatType = false;
    boolean waterDaily = false;
    int repeatTypee = 0;
    private NumberPicker.OnValueChangeListener valueChangeListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker(getActivity());

        try {
            Bundle mArgs = getArguments();
            repeatType = mArgs.getBoolean("repeatType");
            waterDaily = mArgs.getBoolean("waterDaily");
        } catch (NullPointerException ignored) {

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (repeatType) {
            builder.setTitle("Repeat Type");
            repeatTypee = 1;
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(8);
            numberPicker.setDisplayedValues(new String[]{"Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday", "Every Saturday", "Every Sunday", "All Week"});
        } else if (waterDaily) {
            repeatTypee = 1;
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(10);
            numberPicker.setDisplayedValues(new String[]{"1000 ml", "2000 ml", "3000 ml", "4000 ml", "5000 ml", "6000 ml", "7000 ml", "8000 ml", "9000 ml", "10000 ml"});

        } else {
            builder.setTitle("No of Repeat");
            repeatTypee = 0;
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(10);
            numberPicker.setDisplayedValues(new String[]{"1 Times", "2 Times", "3 Times", "4 Times", "5 Times", "6 Times", "7 Times", "8 Times", "9 Times", "10 Times"});

        }


        builder.setPositiveButton("OK", (dialog, which) -> valueChangeListener.onValueChange(numberPicker,
                numberPicker.getValue(), repeatTypee));

        builder.setNegativeButton("CANCEL", (dialog, which) -> {

        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}
