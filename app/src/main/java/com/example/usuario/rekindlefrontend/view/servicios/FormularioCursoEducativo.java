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
import com.example.usuario.rekindlefrontend.utils.FormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;

import java.text.Format;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioCursoEducativo extends Fragment {

    private ArrayList<String> param;

    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eDireccion;
    private EditText eAmbito;
    private EditText eRequisitos;
    private EditText eHorario;
    private EditText ePlazas;
    private EditText ePrecio;
    private EditText eDescripcion;

    public FormularioCursoEducativo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_curso_educativo, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_curso_educativo);
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

        eNombre = view.findViewById(R.id.nombre_curso_educativo);
        eEmail = view.findViewById(R.id.correo_curso_educativo);
        eTelefono = view.findViewById(R.id.telefono_curso_educativo);
        eDireccion = view.findViewById(R.id.direccion_curso_educativo);
        eAmbito = view.findViewById(R.id.ambito_curso_educativo);
        eRequisitos = view.findViewById(R.id.requisitos_curso_educativo);
        eHorario = view.findViewById(R.id.horario_curso_educativo);
        ePlazas = view.findViewById(R.id.plazas_curso_educativo);
        ePrecio = view.findViewById(R.id.precio_curso_educativo);
        eDescripcion = view.findViewById(R.id.descripcion_curso_educativo);

    }

    public void checkCampos(View view) throws Exception {//FALTA CHECK PRECIO!!!

        FormatChecker.checkNombreServicio(eNombre.getText().toString());
        FormatChecker.checkEmail(eEmail.getText().toString());
        FormatChecker.checkTelefonoServicio(eTelefono.getText().toString());
        FormatChecker.checkAmbitoCursoEducativo(eAmbito.getText().toString());
        FormatChecker.checkRequisitosServicio(eRequisitos.getText().toString());
        FormatChecker.checkHorarioCursoEducativo(eHorario.getText().toString());
        FormatChecker.checkPlazasServicio(ePlazas.getText().toString());
        FormatChecker.checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eEmail.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eAmbito.getText().toString());
        param.add (eRequisitos.getText().toString());
        param.add (eHorario.getText().toString());
        param.add (ePlazas.getText().toString());
        param.add (ePrecio.getText().toString());
        param.add (eDescripcion.getText().toString());

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
