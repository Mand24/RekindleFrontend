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
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioOfertaEmpleo extends Fragment {

    private ArrayList<String> param;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

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

        eDireccion = view.findViewById(R.id.direccion_oferta_empleo);
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

        param.add(eDireccion.getText().toString());

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
                result = ComunicacionServicios.crearOfertaEmpleo(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
