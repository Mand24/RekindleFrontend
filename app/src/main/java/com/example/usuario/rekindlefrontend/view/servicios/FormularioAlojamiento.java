package com.example.usuario.rekindlefrontend.view.servicios;


import android.app.DatePickerDialog;
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

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionServicios;
import com.example.usuario.rekindlefrontend.utils.FormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioAlojamiento extends Fragment {

    private ArrayList<String> param;

    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eDireccion;
    private EditText eSolicitudes;
    private EditText eDeadline;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText eDescripcion;

    public FormularioAlojamiento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_alojamiento, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_alojamiento);
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

        SetDate setDate = new SetDate(eDeadline, container.getContext());

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_alojamiento);
        eEmail = view.findViewById(R.id.correo_alojamiento);
        eTelefono = view.findViewById(R.id.telefono_alojamiento);
        eDireccion = view.findViewById(R.id.direccion_alojamiento);
        eSolicitudes = view.findViewById(R.id.solicitudes_alojamiento);
        eDeadline = view.findViewById (R.id.fecha_limite_alojamiento);
        eDescripcion = view.findViewById(R.id.descripcion_alojamiento);

    }

    public void checkCampos(View view) throws Exception {

        FormatChecker.checkNombreServicio(eNombre.getText().toString());
        FormatChecker.checkEmail(eEmail.getText().toString());
        FormatChecker.checkTelefonoServicio(eTelefono.getText().toString());
        FormatChecker.checkSolicitudesServicio(eSolicitudes.getText().toString());
        FormatChecker.checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eEmail.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eSolicitudes.getText().toString());
        param.add (eDeadline.getText().toString());
        param.add (eDescripcion.getText().toString());

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
                result = ComunicacionServicios.crearAlojamiento(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
