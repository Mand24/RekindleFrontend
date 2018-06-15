package com.example.usuario.rekindlefrontend.view.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.service.Valoration;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesRefugeeEnded;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluateService extends AppBaseActivity {

    private TextView serviceName;
    private RatingBar ratingBar;
    private Service service;
    private Valoration mValoration;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_service);

        mAPIService = APIUtils.getAPIService();

        service = (Service) getIntent().getSerializableExtra("Service");

        setViews();

        AppCompatButton button_send = (AppCompatButton) findViewById(R.id
                .evaluateService);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValoration = new Valoration(service.getId(), service.getServiceType(),
                        Consistency.getUser(getApplicationContext()).getMail(),
                        ratingBar.getRating());
                sendCreateValoration();
            }
        });
    }

    private void setViews() {
        serviceName = findViewById(R.id.serviceName);
        ratingBar = findViewById(R.id.ratingBar);

        serviceName.setText(service.getName());
    }

    private void sendCreateValoration() {
        mAPIService.createValoration(Consistency.getUser(this).getApiKey(), service.getId(),
                service
                        .getServiceType(),
                mValoration)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            manageResult(true);
                        } else {
                            manageResult(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        manageResult(false);
                    }
                });
    }

    private void manageResult(Boolean result) {
        if (result) {
            Intent i = new Intent(this, MyServicesRefugeeEnded.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
