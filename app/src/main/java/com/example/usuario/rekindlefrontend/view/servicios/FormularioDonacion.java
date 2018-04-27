package com.example.usuario.rekindlefrontend.view.servicios;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionServicios;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetTime;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioDonacion extends AbstractFormatChecker {

    private ArrayList<String> param;

    private EditText editStartingTime, editEndingTime;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eSolicitudes;
    private EditText eDescripcion;

    public FormularioDonacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_donacion, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_donacion);
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

        eDireccion = view.findViewById(R.id.direccion_donacion);
        eDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete
                            .MODE_OVERLAY).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        editStartingTime = (EditText) view.findViewById(R.id
                .franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        SetTime toTime = new SetTime(editEndingTime, container.getContext());
        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_donacion);
        eEmail = view.findViewById(R.id.correo_donacion);
        eTelefono = view.findViewById(R.id.telefono_donacion);
        eDireccion = view.findViewById(R.id.direccion_donacion);
        eSolicitudes = view.findViewById(R.id.solicitudes_donacion);
        editStartingTime = view.findViewById(R.id.franja_horaria_inicio_donacion);
        editEndingTime = view.findViewById(R.id.franja_horaria_fin_donacion);
        eDescripcion = view.findViewById(R.id.descripcion_donacion);

    }

    public void checkCampos(View view) throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkSolicitudesServicio(eSolicitudes.getText().toString());
        checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros(){
        param.add(eDireccion.getText().toString());

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eEmail.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eSolicitudes.getText().toString());
        param.add(editStartingTime.getText().toString());
        param.add(editEndingTime.getText().toString());
        param.add (eDescripcion.getText().toString());

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i("==================", "Place: " + place.getName());
                eDireccion.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i("==================", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
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
