package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListarServicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_servicios);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }
}
