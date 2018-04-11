package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CambiarPassword extends AppCompatActivity {

    private ArrayList<String> param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);

        Button b = (Button) findViewById(R.id.guardar_cambiar_password);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (comprobarCampos()){
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

    public boolean comprobarCampos(){
        return true;
    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        EditText editText = (EditText) findViewById(R.id.actual_password);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.new_password);
        param.add(editText.getText().toString());

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
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
