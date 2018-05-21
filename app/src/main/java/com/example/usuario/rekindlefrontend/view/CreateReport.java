package com.example.usuario.rekindlefrontend.view;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReport extends AppCompatActivity {

    private TextView reportedUser;
    private EditText motive;

    private Report report;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        setViews();

        AppCompatButton send_report = findViewById(R.id.send_report);
        send_report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    createReport();
                }
                catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                sendReport();

            }

        });
    }

    public void setViews(){
        reportedUser = findViewById(R.id.reported_user);
        motive = findViewById(R.id.motive);
    }

    public void createReport(){
        Usuario user = getUser(getApplicationContext());
        report = new Report (user, , motive.getText().toString());
    }

    public void sendReport(){
        mAPIService.createReport(report).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("llamada "+call.toString());
                if (response.isSuccessful()){
                    tratarResultadoPeticion(true);
                }else {
                    System.out.println("codi "+response.code());
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "this is an actual network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.reporte_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.reporte_fallido), Toast.LENGTH_SHORT).show();
        }
    }
}
