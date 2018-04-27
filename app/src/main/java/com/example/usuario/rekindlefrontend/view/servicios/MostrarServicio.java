package com.example.usuario.rekindlefrontend.view.servicios;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Servicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostrarServicio extends AppCompatActivity implements OnMapReadyCallback {

    Fragment tipoServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);


        Servicio servicio = (Servicio) getIntent().getSerializableExtra("Servicio");

    }

}
