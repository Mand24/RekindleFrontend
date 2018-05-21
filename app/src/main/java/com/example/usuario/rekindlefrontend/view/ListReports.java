package com.example.usuario.rekindlefrontend.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;

import java.util.ArrayList;
import java.util.List;

public class ListReports extends AppCompatActivity {

    protected List<Report> reports = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);
    }

    public void onBackPressed(){
        finish();
    }
}
