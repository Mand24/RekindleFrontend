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
public class FormularioOfertaEmpleo extends Fragment {

    private ArrayList<String> param;

    public FormularioOfertaEmpleo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_oferta_empleo, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_oferta_empleo);
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

        EditText editText = (EditText) view.findViewById(R.id.nombre_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.correo_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.telefono_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.direccion_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.puesto_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.requisitos_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.jornada_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.horas_semanales_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.duracion_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.sueldo_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.plazas_oferta_empleo);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.descripcion_oferta_empleo);
        param.add(editText.getText().toString());

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.servicio_alojamiento_fallido), Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            boolean result = false;
            try {
                result = ComunicacionServicios.crearOfertaEmpleo(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
