package com.example.usuario.rekindlefrontend.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;

public class ShowReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
