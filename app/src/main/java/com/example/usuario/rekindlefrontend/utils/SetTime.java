package com.example.usuario.rekindlefrontend.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetTime implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    public SetTime(EditText editText, Context ctx){
        this.editText = editText;
        this.editText.setOnClickListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;

    }

    @Override
    public void onClick(View v) {
        new TimePickerDialog(ctx, this, myCalendar.get(Calendar.HOUR_OF_DAY)
                , myCalendar.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hora, minuto;
        if (hourOfDay < 10) {

            hora = "0" + hourOfDay;
        } else {
            hora = String.valueOf(hourOfDay);
        }

        if (minute < 10) {

            minuto = "0" + minute;
        } else {
            minuto = String.valueOf(minute);
        }
        this.editText.setText( hora + ":" + minuto+":00");
    }

}
