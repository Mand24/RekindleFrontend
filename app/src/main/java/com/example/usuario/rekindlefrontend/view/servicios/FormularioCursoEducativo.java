package com.example.usuario.rekindlefrontend.view.servicios;


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

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionServicios;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioCursoEducativo extends Fragment {

    private ArrayList<String> param;

    public FormularioCursoEducativo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_curso_educativo, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_curso_educativo);
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

        EditText editText = (EditText) view.findViewById(R.id.nombre_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.correo_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.telefono_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.direccion_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.ambito_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.requisitos_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.horario_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.plazas_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.precio_curso_educativo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.descripcion_curso_educativo);
        param.add(editText.getText().toString());

    }
    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.curso_educativo_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.curso_educativo_fallido), Toast.LENGTH_SHORT).show();
    }


    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            boolean result = false;
            try {
                result = ComunicacionServicios.crearCursoEducativo(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
