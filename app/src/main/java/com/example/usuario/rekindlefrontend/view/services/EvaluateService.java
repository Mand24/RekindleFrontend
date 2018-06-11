package com.example.usuario.rekindlefrontend.view.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;

public class EvaluateService extends AppCompatActivity {

    private Service serviceEx = new Lodge (1, "voluntario@gmail.com", "alojamiento", "desc",
            "Carrer Infern d'en Parera, 2, 08348 Cabrils, Barcelona", "12","12-03-2018", "1234");

    Refugee refugeeEx = new Refugee ("refugiado@gmail.com", "pass1234", "refugiadoName",
            "refugiadoSurname","refugadioSecondSurname", "foto", "123456789","12-09-2018","Male",
            "Barcelona","cabrils","ethinc","+A","","");

    private TextView  serviceName;
    private RatingBar ratingBar;
    private EditText  serviceOpinion;

    private Refugee refugee;
    private Service service;
    private Float   rating;
    private String  opinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_service);

        refugee = refugeeEx;
        service = serviceEx;

        setViews ();

        AppCompatButton button_send = (AppCompatButton) findViewById(R.id
                .evaluateService);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    rating  = ratingBar.getRating();

                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setViews () {
        serviceName     = findViewById (R.id.serviceName);
        ratingBar       = findViewById (R.id.ratingBar);

        serviceName.setText (service.getName());
    }
}
