package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarPerfil extends AppCompatActivity {

    private ArrayList<String> param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        //establecer parametros a los spinners
        setSpinners();

        Button b = (Button) findViewById(R.id.guardar_editar_perfil);
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

        Button b2 = (Button) findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CambiarPassword.class);
                startActivity(i);
            }
        });


    }

    public void setSpinners(){

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

    public boolean comprobarCampos(){
        return true;
    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        EditText editText = (EditText) findViewById(R.id.nombre_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.apellido1_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.apellido2_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.email_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.telefono_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.naciminento_usuario_perfil);
        param.add(editText.getText().toString());

        Spinner spinner = (Spinner) findViewById(R.id.sexo_usuario_perfil);
        param.add(spinner.getSelectedItem().toString());

        editText = (EditText) findViewById(R.id.pais_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.pueblo_usuario_perfil);
        param.add(editText.getText().toString());

        editText = (EditText) findViewById(R.id.etnia_usuario_perfil);
        param.add(editText.getText().toString());

        spinner = (Spinner) findViewById(R.id.sangre_usuario_perfil);
        param.add(spinner.getSelectedItem().toString());

        spinner = (Spinner) findViewById(R.id.ojos_usuario_perfil);
        param.add(spinner.getSelectedItem().toString());

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), VerPerfil.class);
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
                result = ComunicacionUsuarios.modificarPerfil(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
            
        }
    }
}
