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
import com.example.usuario.rekindlefrontend.utils.SetTime;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioDonacion extends Fragment {

    private ArrayList<String> param;

    EditText editStartingTime, editEndingTime;

    public FormularioDonacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_donacion, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_donacion);
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

        editStartingTime = (EditText) view.findViewById(R.id
                .franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        editEndingTime = (EditText) view.findViewById(R.id
                .franja_horaria_fin_donacion);
        SetTime toTime = new SetTime(editEndingTime, container.getContext());
        return view;
    }

    public boolean comprobarCampos(){
        return true;
    }

    public void obtenerParametros(View view){

        param = new ArrayList<String>();

        EditText editText = (EditText) view.findViewById(R.id.nombre_donacion);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.correo_donacion);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.telefono_donacion);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.direccion_donacion);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.solicitudes_donacion);
        param.add(editText.getText().toString());

        param.add(editStartingTime.getText().toString());

        param.add(editEndingTime.getText().toString());

        editText = (EditText) view.findViewById(R.id.descripcion_donacion);
        param.add(editText.getText().toString());

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.donacion_creada_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.donacion_fallida), Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            boolean result = false;
            try {
                result = ComunicacionServicios.crearDonacion(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
