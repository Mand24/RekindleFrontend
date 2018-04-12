package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class VerPerfil extends AppCompatActivity {

    private Refugiado refugiado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        try {
            refugiado = new AsyncTaskCall().execute().get();
            tratarResultadoPeticion();
        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AppCompatButton b = (AppCompatButton) findViewById(R.id.editar_ver_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
                i.putExtra("Refugiado", refugiado);
                startActivity(i);
            }

        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

    public void tratarResultadoPeticion(){

        TextView tv = findViewById(R.id.email_usuario_perfil);
        tv.setText(refugiado.getMail());

        tv = findViewById(R.id.nombre_usuario_perfil);
        tv.setText(refugiado.getName());

        tv = findViewById(R.id.apellido1_usuario_perfil);
        tv.setText(refugiado.getSurname1());

        tv = findViewById(R.id.apellido2_usuario_perfil);
        tv.setText(refugiado.getSurname2());

        tv = findViewById(R.id.telefono_usuario_perfil);
        tv.setText(refugiado.getPhoneNumber());

        tv = findViewById(R.id.naciminento_usuario_perfil);
        tv.setText(refugiado.getBirthDate());

        tv = findViewById(R.id.sexo_usuario_perfil);
        tv.setText(refugiado.getSex());

        tv = findViewById(R.id.pais_usuario_perfil);
        tv.setText(refugiado.getCountry());

        tv = findViewById(R.id.pueblo_usuario_perfil);
        tv.setText(refugiado.getTown());

        tv = findViewById(R.id.etnia_usuario_perfil);
        tv.setText(refugiado.getEthnic());

        tv = findViewById(R.id.sangre_usuario_perfil);
        tv.setText(refugiado.getBloodType());

        tv = findViewById(R.id.ojos_usuario_perfil);
        tv.setText(refugiado.getEyeColor());
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Refugiado> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Refugiado doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            Refugiado result = new Refugiado();
            try {
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String param = datos.getString("email", "email");
                result = ComunicacionUsuarios.verPerfil(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }


}
