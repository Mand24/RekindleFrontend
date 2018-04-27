package com.example.usuario.rekindlefrontend.view.usuarios;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;
import com.example.usuario.rekindlefrontend.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroVoluntario extends AbstractFormatChecker {

    private ArrayList<String> param;

    private EditText eNombre;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRPassword;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;

    public RegistroVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_voluntario, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    checkCampos(view);
                    obtenerParametros();
                    boolean result = new AsyncTaskCall().execute().get();
                    tratarResultadoPeticion(result);
                    //tratarResultadoPeticion(true);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_voluntario);
        eEmail = view.findViewById(R.id.email_voluntario);
        ePassword = view.findViewById(R.id.password_voluntario);
        eRPassword = view.findViewById(R.id.rpassword_voluntario);
        ePrimer_apellido = view.findViewById(R.id.p_apellido_voluntario);
        eSegundo_apellido = view.findViewById(R.id.s_apellido_voluntario);

    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPassword(ePassword.getText().toString(), eRPassword.getText().toString
                ());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());

    }

    public void obtenerParametros(){


        /*param.add("refa@gmail.com");
        param.add("sergimanel");
        param.add("refa");
        param.add("garcia");
        param.add("monserrate");*/

        param = new ArrayList<String>();
        param.add(eEmail.getText().toString());
        param.add(ePassword.getText().toString());
        param.add(eNombre.getText().toString());
        param.add(ePrimer_apellido.getText().toString());
        param.add(eSegundo_apellido.getText().toString());

        //System.out.println("nombre: " + nombre);

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registrado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), PantallaInicio.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.registro_fallido), Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            boolean result = false;
            try {
                result = ComunicacionUsuarios.registrarVoluntario(url, param);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
