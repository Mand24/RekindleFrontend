package com.example.usuario.rekindlefrontend.view.moderate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
import com.example.usuario.rekindlefrontend.data.remote.APIService;

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
    }

    public void setViews() {
        requesterUser = findViewById(R.id.informer_user);
        requestedDonation = findViewById(R.id.reported_user);
        motive = findViewById(R.id.motive);
    }

    public void initializeFields() {
        requesterUser.setText(donationRequest.getUser().getMail());
        requestedDonation.setText(donationRequest.getDonation().getName());
        motive.setText(donationRequest.getMotive());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
