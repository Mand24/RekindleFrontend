package com.example.usuario.rekindlefrontend;


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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroVoluntario extends Fragment {

    private ArrayList<String> param;

    public RegistroVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_voluntario, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (comprobarCampos()) {
                    try {
                        obtenerParametros(view);
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

        return view;
    }

    public boolean comprobarCampos(){
        return true;
    }

    public void obtenerParametros(View view){

        param = new ArrayList<String>();
        /*param.add("pedrito");
            param.add("pedrito@gmail.com");
            param.add("sergimanel");
            param.add("garcia");
            param.add("monserrate");*/

        EditText editText = (EditText) view.findViewById(R.id.nombre_voluntario);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.email_voluntario);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.password_voluntario);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.p_apellido_voluntario);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.s_apellido_voluntario);
        param.add(editText.getText().toString());

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
