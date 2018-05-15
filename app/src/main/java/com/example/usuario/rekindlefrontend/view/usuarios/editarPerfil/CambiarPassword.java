package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPassword extends AppCompatActivity {

    private String tipo;
    private Refugiado refugiado;
    private Voluntario voluntario;

    private ArrayList<String> param;

    private String actual;
    private String new_pass;
    private String email;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);

        mAPIService = APIUtils.getAPIService();

        tipo = getIntent().getStringExtra("tipo");

        if (tipo.equals("Refugee")) {
            refugiado = (Refugiado) getIntent().getParcelableExtra("Refugiado");
            email = refugiado.getMail();
        }
        else {
            voluntario = (Voluntario) getIntent().getParcelableExtra("Voluntario");
            email = voluntario.getMail();
        }


        AppCompatButton b = (AppCompatButton) findViewById(R.id.guardar_cambiar_password);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (setCampos()){
                    /*try{
                        obtenerParametros();
                        boolean result = new AsyncTaskCall().execute().get();
                        tratarResultadoPeticion(result);
                    }catch (Exception e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }*/
                   sendCambiarPassword();
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

    public void sendCambiarPassword() {
        mAPIService.cambiarPassword(email, actual, new_pass).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    tratarResultadoPeticion(true);
                }
                else {
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.guardado_correctamente), Toast.LENGTH_SHORT).show();
            if (tipo.equals("Refugee")) {
                refugiado.setPassword(new_pass);
                Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
                i.putExtra("Refugiado", refugiado);
                i.putExtra("tipo", "Refugee");
                startActivity(i);
            }
            else {
                voluntario.setPassword(new_pass);
                Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
                i.putExtra("Voluntario", voluntario);
                i.putExtra("tipo", "Volunteer");
                startActivity(i);
            }


        }else Toast.makeText(getApplicationContext(), getResources().getString(R
                .string.guardado_fallido), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
        startActivity(i);
    }
}
