package com.example.usuario.rekindlefrontend.view.users.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppBaseActivity {

    private String userType;
    private Refugee mRefugee;
    private Volunteer mVolunteer;

    private String actual;
    private String new_pass;
    private String email;
    private String apiKey;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setTitle(R.string.cambiar_password);

        mAPIService = APIUtils.getAPIService();

        userType = getIntent().getStringExtra("userType");

        System.out.println(userType);

        if (userType.equals("Refugee")) {
            mRefugee = (Refugee) getIntent().getParcelableExtra("Refugee");
            email = mRefugee.getMail();
            apiKey = Consistency.getUser(this).getApiKey();
        } else {
            mVolunteer = (Volunteer) getIntent().getParcelableExtra("Volunteer");
            email = mVolunteer.getMail();
            apiKey = Consistency.getUser(this).getApiKey();
        }


        AppCompatButton b = (AppCompatButton) findViewById(R.id.guardar_cambiar_password);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setFields()) {

                    sendChangePassword();
                }
            }
        });
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public boolean setFields() {
        EditText container_data;
        Context context = getApplicationContext();
        String text;
        String text_aux;

        container_data = findViewById(R.id.actual_password);
        text = container_data.getText().toString();

        if (text.length() == 0 || text.length() < 4) {
            Toast.makeText(context, "Contraseña actual introducida no valida", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            actual = text;
        }

        container_data = findViewById(R.id.new_password);
        text = container_data.getText().toString();

        container_data = findViewById(R.id.repeat_password);
        text_aux = container_data.getText().toString();

        if (text.length() == 0) {
            Toast.makeText(context, "Introduzca la nueva contraseña", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (text.length() < 4) {
            Toast.makeText(context, "Nueva contraseña demasiada corta, mínimo 4 caracteres", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (!text.equals(text_aux)) {
            Toast.makeText(context, "La nueva contraseña no coincide", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            new_pass = text;
        }

        return true;
    }

    public void sendChangePassword() {
        mAPIService.cambiarPassword(apiKey, email, actual, new_pass).enqueue(new Callback<Void>() {
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
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void manageResult(boolean result) {

        if (result) {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_correctamente), Toast.LENGTH_SHORT).show();
            if (userType.equals("Refugee")) {
                mRefugee.setPassword(new_pass);
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("Refugee", mRefugee);
                startActivity(i);
            } else {
                mVolunteer.setPassword(new_pass);
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("Volunteer", mVolunteer);
                startActivity(i);
            }


        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_fallido), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
