package com.example.usuario.rekindlefrontend.view.usuarios;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Refugiado;
import com.example.usuario.rekindlefrontend.view.CambiarPassword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (setCampos()){
                    try{
                        //cogerDatos();
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
                i.putExtra("Refugiado", refugiado);
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

    public boolean letras (String texto)
    {
        Pattern patron = Pattern.compile ("^[a-zA-Z]+$");
        Matcher valid  = patron.matcher  (texto);
        return valid.matches ();
    }

    public boolean numeros (String texto)
    {
        Pattern patron = Pattern.compile ("^[0-9]+$");
        Matcher valid  = patron.matcher  (texto);
        return valid.matches ();
    }

    public boolean fecha_valida (String fecha)
    {
        Pattern patron = Pattern.compile ("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        Matcher valid  = patron.matcher  (fecha);
        return valid.matches ();
    }

    public boolean setCampos ()
    {
        EditText container_data;
        Context context = getApplicationContext ();
        Spinner  spinner;
        String   texto;

        // no control: sexo, grupo sanguineo, ojos

        spinner         = findViewById (R.id.sangre_usuario_perfil);
        refugiado.setBloodType(spinner.getSelectedItem().toString());
        //grupo_sanguineo = spinner.getSelectedItem ().toString ();
        spinner         = findViewById (R.id.ojos_usuario_perfil);
        refugiado.setEyeColor(spinner.getSelectedItem().toString());
        //ojos            = spinner.getSelectedItem ().toString ();
        spinner         = findViewById (R.id.sexo_usuario_perfil);
        refugiado.setSex(spinner.getSelectedItem().toString());
        //sexo            = spinner.getSelectedItem ().toString ();

        // control nombre

        container_data = findViewById (R.id.nombre_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
            Toast.makeText (context, "Nombre obligatorio", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 20) {
            Toast.makeText(context, "Nombre es demasiado largo, máximo 20 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "El nombre solo puede contener letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setName(texto); }

        // control email

        container_data = findViewById (R.id.email_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0 ) {
            Toast.makeText(context, "Email obligatorio", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 30) {
            Toast.makeText(context, "Email demasiado largo, máximo 30 caracteres", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher (texto).matches
                        ()) {
            Toast.makeText(context, "Formato de email no valido", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setMail(texto); }

        // control telefono

        container_data = findViewById (R.id.
                telefono_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (!numeros (texto) && texto.length () > 0) {
            Toast.makeText(context, "Teléfono solo puede contener dígitos", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 40) {
            Toast.makeText(context, "Teléfono demasiado largo, máximo 50 números", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setPhoneNumber(texto); }

        // control nacimiento

        container_data = findViewById (R.id.
                naciminento_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (!fecha_valida (texto) && texto.length () > 0) {
            Toast.makeText(context, "Formato fecha incorrecto; formato correcto = dd-mm-aaaa", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setBirthDate(texto); }

        // control procedencia

        container_data = findViewById (R.id.
                pais_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (texto.length () > 20) {
            Toast.makeText(context, "País de origen demasiado largo, máximo 20 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre del país de origen solo puede contener letras",
                    Toast
                            .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setCountry(texto); }

        // control pueblo

        container_data = findViewById (R.id.
                pueblo_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (texto.length () > 40) {
            Toast.makeText(context, "Nombre del pueblo demasiado largo, máximo 40 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre del pueblo solo puede contener letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setTown(texto); }

        // control etnia

        container_data = findViewById (R.id.
                etnia_usuario_perfil);
        texto = container_data.getText ().toString ();

        if (texto.length () > 20) {
            Toast.makeText(context, "Nombre de la etnia demasiado largo, máximo 20 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre de la etnia de origen solo puede contener letras",
                    Toast.LENGTH_SHORT).show ();
            return false;
        }
        else { refugiado.setEthnic(texto); }

        return true;
    }

    public void cogerDatos(){
        EditText editText = (EditText) findViewById(R.id.nombre_usuario_perfil);
        refugiado.setName(editText.getText().toString());

        editText = (EditText) findViewById(R.id.apellido1_usuario_perfil);
        refugiado.setSurname1(editText.getText().toString());

        editText = (EditText) findViewById(R.id.apellido2_usuario_perfil);
        refugiado.setSurname2(editText.getText().toString());

        editText = (EditText) findViewById(R.id.email_usuario_perfil);
        refugiado.setMail(editText.getText().toString());

        editText = (EditText) findViewById(R.id.telefono_usuario_perfil);
        refugiado.setPhoneNumber(editText.getText().toString());

        editText = (EditText) findViewById(R.id.naciminento_usuario_perfil);
        refugiado.setBirthDate(editText.getText().toString());

        Spinner spinner = (Spinner) findViewById(R.id.sexo_usuario_perfil);
        refugiado.setSex(spinner.getSelectedItem().toString());

        editText = (EditText) findViewById(R.id.pais_usuario_perfil);
        refugiado.setCountry(editText.getText().toString());

        editText = (EditText) findViewById(R.id.pueblo_usuario_perfil);
        refugiado.setTown(editText.getText().toString());

        editText = (EditText) findViewById(R.id.etnia_usuario_perfil);
        refugiado.setEthnic(editText.getText().toString());

        spinner = (Spinner) findViewById(R.id.sangre_usuario_perfil);
        refugiado.setBloodType(spinner.getSelectedItem().toString());

        spinner = (Spinner) findViewById(R.id.ojos_usuario_perfil);
        refugiado.setEyeColor(spinner.getSelectedItem().toString());

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