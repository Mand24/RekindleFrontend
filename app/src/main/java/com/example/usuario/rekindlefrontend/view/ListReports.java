package com.example.usuario.rekindlefrontend.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ReportsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

public class ListReports extends AppCompatActivity {

    protected List<Report> reports = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected APIService mAPIService;
    protected ReportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
    }

    public void onBackPressed(){
        finish();
    }
}
