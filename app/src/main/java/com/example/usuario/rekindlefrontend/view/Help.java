package com.example.usuario.rekindlefrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;

public class Help extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //TextView tvTitulo = findViewById(R.id.titulo_help);
        //TextView tvContenido = findViewById(R.id.contenido_help);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(Help.this, PantallaInicio.class);
        startActivity(i);
    }
}