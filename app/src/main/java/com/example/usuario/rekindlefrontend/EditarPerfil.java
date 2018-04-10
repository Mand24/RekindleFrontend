package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        Button b = (Button) findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VerPerfil.class);
                startActivity(i);
            }
        });

        Button b2 = (Button) findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CambiarPassword.class);
                startActivity(i);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.sexo_usuario_perfil);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_sexo, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.sangre_usuario_perfil);
        adapter = ArrayAdapter.createFromResource(this, R.array.lista_grupo_sanguineo, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.ojos_usuario_perfil);
        adapter = ArrayAdapter.createFromResource(this, R.array.lista_color_ojos, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
