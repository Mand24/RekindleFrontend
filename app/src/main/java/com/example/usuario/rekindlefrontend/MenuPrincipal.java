package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button button_listar_servicios = (Button) findViewById(R.id.listar_servicios_MenuPrincipal);
        button_listar_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListarServicios.class);
                startActivity(i);
            }
        });

        Button button_crear_servicio = (Button) findViewById(R.id.crear_servicio_MenuPrincipal);
        button_crear_servicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CrearServicio.class);
                startActivity(i);
            }
        });

        Button button_ver_perfil = (Button) findViewById(R.id.ver_perfil_MenuPrincipal);
        button_ver_perfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VerPerfil.class);
                startActivity(i);
            }
        });
    }
}
