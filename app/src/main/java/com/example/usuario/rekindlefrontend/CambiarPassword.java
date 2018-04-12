package com.example.usuario.rekindlefrontend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CambiarPassword extends AppCompatActivity {

    private Refugiado refugiado;

    private ArrayList<String> param;

    private String actual;
    private String new_pass;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);

        refugiado = (Refugiado) getIntent().getSerializableExtra("Refugiado");

        email = refugiado.getMail();


        AppCompatButton b = (AppCompatButton) findViewById(R.id.guardar_cambiar_password);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (setCampos()){
                    try{
                        obtenerParametros();
                        boolean result = new AsyncTaskCall().execute().get();
                        tratarResultadoPeticion(result);
                    }catch (Exception e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {

                }
            }
        });
    }

    public boolean letras (String texto)
    {
        Pattern patron = Pattern.compile ("^[a-zA-Z]+$");
        Matcher valid  = patron.matcher  (texto);
        return  valid.matches ();
    }

    public boolean setCampos ()
    {
        EditText container_data;
        Context context = getApplicationContext();
        String  texto;
        String  texto_aux;

        container_data = findViewById(R.id.actual_password);
        texto = container_data.getText().toString();

        if (texto.length () == 0 || texto.length() < 4) {
            Toast.makeText(context, "Contraseña actual introducida no valida", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { actual = texto; }

        container_data = findViewById(R.id.new_password);
        texto = container_data.getText().toString();

        container_data = findViewById(R.id.repeat_password);
        texto_aux = container_data.getText().toString();

        if (texto.length () == 0) {
            Toast.makeText (context, "Introduzca la nueva contraseña", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () < 4) {
            Toast.makeText(context, "Nueva contraseña demasiada corta, mínimo 4 caracteres", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!texto.equals (texto_aux))
        {
            Toast.makeText(context, "La nueva contraseña no coincide", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { new_pass = texto; }

        return true;
    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        param.add(email);
        param.add (actual);
        param.add (new_pass);

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {
            refugiado.setPassword(new_pass);
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
            i.putExtra("Refugiado", refugiado);
            startActivity(i);

        }else Toast.makeText(getApplicationContext(), getResources().getString(R
                .string.guardado_fallido), Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            boolean result = false;
            try {
                result = ComunicacionUsuarios.cambiarPassword(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
        startActivity(i);
    }
}
