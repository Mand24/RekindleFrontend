package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarPerfil extends AppCompatActivity {

    private Refugiado refugiado;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        refugiado = (Refugiado) getIntent().getSerializableExtra("Refugiado");
        //establecer parametros a los spinners
        setSpinners();

        initializeData();

        AppCompatButton b = (AppCompatButton) findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (comprobarCampos()){
                    try{
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

        AppCompatButton b2 = (AppCompatButton) findViewById(R.id.cambiar_password);
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
        adapter1 = ArrayAdapter.createFromResource(this, R.array
                .lista_sexo, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        spinner = (Spinner) findViewById(R.id.sangre_usuario_perfil);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.lista_grupo_sanguineo, R.layout
                .spinner_item);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        spinner = (Spinner) findViewById(R.id.ojos_usuario_perfil);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.lista_color_ojos, R.layout
                .spinner_item);
        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner.setAdapter(adapter3);
    }

    public boolean comprobarCampos(){
        return true;
    }

    public void initializeData(){

        EditText editText = (EditText) findViewById(R.id.nombre_usuario_perfil);
        editText.setText(refugiado.getName());

        editText = (EditText) findViewById(R.id.apellido1_usuario_perfil);
        editText.setText(refugiado.getSurname1());

        editText = (EditText) findViewById(R.id.apellido2_usuario_perfil);
        editText.setText(refugiado.getSurname2());

        editText = (EditText) findViewById(R.id.email_usuario_perfil);
        editText.setText(refugiado.getMail());

        editText = (EditText) findViewById(R.id.telefono_usuario_perfil);
        editText.setText(refugiado.getPhoneNumber());

        editText = (EditText) findViewById(R.id.naciminento_usuario_perfil);
        editText.setText(refugiado.getBirthDate());

        Spinner spinner = (Spinner) findViewById(R.id.sexo_usuario_perfil);
        int selectionPosition = adapter1.getPosition(refugiado.getSex());
        spinner.setSelection(selectionPosition);


        editText = (EditText) findViewById(R.id.pais_usuario_perfil);
        editText.setText(refugiado.getCountry());

        editText = (EditText) findViewById(R.id.pueblo_usuario_perfil);
        editText.setText(refugiado.getTown());

        editText = (EditText) findViewById(R.id.etnia_usuario_perfil);
        editText.setText(refugiado.getEthnic());

        spinner = (Spinner) findViewById(R.id.sangre_usuario_perfil);
        selectionPosition = adapter2.getPosition(refugiado.getBloodType());
        spinner.setSelection(selectionPosition);

        spinner = (Spinner) findViewById(R.id.ojos_usuario_perfil);
        selectionPosition = adapter3.getPosition(refugiado.getEyeColor());
        spinner.setSelection(selectionPosition);

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
                result = ComunicacionUsuarios.modificarPerfil(url, refugiado);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
            
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), VerPerfil.class);
        startActivity(i);
    }
}


/*
int selectionPosition= adapter.getPosition("YOUR_VALUE");
spinner.setSelection(selectionPosition);
 */