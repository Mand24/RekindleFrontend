package com.example.usuario.rekindlefrontend.view.moderate;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDonationRequest extends AppCompatActivity {

    private TextView donationName;
    private EditText motive;
    private Donation donation;
    private DonationRequest request;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_donation_request);

        setViews();

        donation = getIntent().getParcelableExtra("Donation");

        fillTextViews();

        AppCompatButton send_report = findViewById(R.id.send_report);
        send_report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    createRequest();
                    sendRequest();
                } catch (Exception e){
                    motive.setError("Field is empty");
                }

            }

        });
    }

    void setViews(){
        donationName = findViewById(R.id.requested_donation);
        motive = findViewById(R.id.motive);
        mAPIService = APIUtils.getAPIService();
    }

    public void fillTextViews() {
        donationName.setText(donation.getName());
    }

    void createRequest() throws Exception{
        User user = getUser(getApplicationContext());
        if(!TextUtils.isEmpty(motive.getText().toString())) {
            request = new DonationRequest(user, donation, motive.getText().toString());
            System.out.println("donationRequest " + request.toString());
        }
        else {
            throw new Exception("Report Empty");
        }
    }

    void sendRequest(){
        mAPIService.createDonationRequest(Consistency.getUser(this).getApiKey(), request).enqueue
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
                    .string.solicitud_creada_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.solicitud_fallida), Toast.LENGTH_SHORT).show();
        }
    }
}
