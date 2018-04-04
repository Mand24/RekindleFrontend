package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CambiarPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
        startActivity(i);
    }
}
