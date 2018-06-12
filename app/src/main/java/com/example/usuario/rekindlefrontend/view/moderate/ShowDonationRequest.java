package com.example.usuario.rekindlefrontend.view.moderate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
import com.example.usuario.rekindlefrontend.data.remote.APIService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDonationRequest extends AppCompatActivity {

    private TextView requesterUser;
    private TextView requestedDonation;
    private TextView motive;

    private APIService mAPIService;
    private DonationRequest donationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_donation_request);

        setViews();

        donationRequest = getIntent().getParcelableExtra("DonationRequest");
        System.out.println(donationRequest.toString());

        initializeFields();

        AppCompatButton accept_request = findViewById(R.id.accept);
        accept_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptRequest();
            }
        });

        AppCompatButton reject_request = findViewById(R.id.reject);
        reject_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectRequest();
            }
        });
    }

    public void setViews() {
        requesterUser = findViewById(R.id.informer_user);
        requestedDonation = findViewById(R.id.reported_user);
        motive = findViewById(R.id.motive);
    }

    public void initializeFields() {
        requesterUser.setText(donationRequest.getRefugeeMail());
        requestedDonation.setText(donationRequest.getDonation().getName());
        motive.setText(donationRequest.getMotive());
    }

    public void acceptRequest(){
        mAPIService.acceptDonationRequest(donationRequest.getDonation().getId(), donationRequest
                .getRefugeeMail()).enqueue
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

    public void rejectRequest(){
        mAPIService.rejectDonationRequest(donationRequest.getDonation().getId(), donationRequest
                .getRefugeeMail()).enqueue(new Callback<Void>() {
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
                    .string.solicitud_gestionada_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), ListDonationRequests.class);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.solicitud_gestion_fallida), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
