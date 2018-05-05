package com.example.usuario.rekindlefrontend.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class SetDate implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    public SetDate(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnClickListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;

    }

    @Override
    public void onClick(View v) {
        new DatePickerDialog(ctx, this, myCalendar.get(Calendar.YEAR)
                , myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String month, day;
        monthOfYear+=1;

        if (monthOfYear < 10) {

            month = "0" + monthOfYear;
        } else {
            month = String.valueOf(monthOfYear);
        }

        if (dayOfMonth < 10) {

            day = "0" + dayOfMonth;
        } else {
            day = String.valueOf(dayOfMonth);
        }

        //this.editText.setText(day + "-" + month + "-" + year);
        this.editText.setText(year + "-" + month + "-" + day);
    }
}
