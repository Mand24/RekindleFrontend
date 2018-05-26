package com.example.usuario.rekindlefrontend.view.moderate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.data.remote.APIService;

public class ShowReport extends AppCompatActivity {

    private TextView informerUser;
    private TextView reportedUser;
    private TextView motive;

    private APIService mAPIService;
    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_report);

        setViews();

        report = (Report) getIntent().getParcelableExtra("Report");
        System.out.println(report.toString());

        initializeFields();

    }

    public void setViews() {
        informerUser = findViewById(R.id.informer_user);
        reportedUser = findViewById(R.id.reported_user);
        motive = findViewById(R.id.motive);
    }


    public void initializeFields() {
        informerUser.setText(report.getInformerUserMail());
        reportedUser.setText(report.getReportedUserMail());
        motive.setText(report.getMotive());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
