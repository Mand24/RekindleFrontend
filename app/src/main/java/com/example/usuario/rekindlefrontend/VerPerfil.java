package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class VerPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        Button b = (Button) findViewById(R.id.editar_ver_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
                startActivity(i);
            }

        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }


}
