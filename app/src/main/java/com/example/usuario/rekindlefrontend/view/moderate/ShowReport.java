package com.example.usuario.rekindlefrontend.view.moderate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.misc.Report;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowReport extends AppBaseActivity {

    private TextView informerUser;
    private TextView reportedUser;
    private TextView motive;
    private AppCompatButton delete;

    private APIService mAPIService;
    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_report);

        setViews();

        report = (Report) getIntent().getParcelableExtra("Report");
        System.out.println(report.toString());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReport();
            }
        });

        initializeFields();

    }

    public void setViews() {
        informerUser = findViewById(R.id.informer_user);
        reportedUser = findViewById(R.id.reported_user);
        motive = findViewById(R.id.motive);
        delete = findViewById(R.id.delete);

        mAPIService = APIUtils.getAPIService();
    }


    public void initializeFields() {
        informerUser.setText(report.getInformerUserMail());
        reportedUser.setText(report.getReportedUserMail());
        motive.setText(report.getMotive());
    }

    public void deleteReport(){
        mAPIService.deleteReport(Consistency.getUser(this).getApiKey(), report.getIdReport(),
                Consistency.getUser(this).getMail())
                .enqueue
                (new
                         Callback<Void>() {
                             @Override
                             public void onResponse(Call<Void> call, Response<Void> response) {
                                 System.out.println("llamada " + call.toString());
                                 if (response.isSuccessful()) {
                                     manageResult(true);
                                 } else {
                                     System.out.println("codi " + response.code());
                                     manageResult(false);
                                 }
                             }

                             @Override
                             public void onFailure(Call<Void> call, Throwable t) {

                                 if (t instanceof IOException) {
                                     Toast.makeText(getApplicationContext(), "this is an actual network failure"
                                             + " :( inform "
                                             + "the user and "
                                             + "possibly retry", Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(getApplicationContext(), "conversion issue! big problems :(",
                                             Toast.LENGTH_SHORT).show();

                                 }
                             }
                         });
    }

    public void manageResult(boolean result) {

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.reporte_eliminado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), ListReports.class);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.reporte_eliminado_fallido), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
