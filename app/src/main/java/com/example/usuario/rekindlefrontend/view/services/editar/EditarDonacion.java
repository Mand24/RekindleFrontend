package com.example.usuario.rekindlefrontend.view.services.editar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetTime;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class EditarDonacion extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Donation servicio;

    private EditText editStartingTime, editEndingTime;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eSolicitudes;
    private EditText eDescripcion;

    public EditarDonacion () {

        }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
        savedInstanceState) {
        final View view = inflater.inflate (R.layout.fragment_editar_donacion, container, false);

        // set : SERVICIO_DONACION

        /*servicio = new Donation (1111, "nombrePD", "descrpcionPD", "direccionPD", "solicitudesPD",
                "13:00IncioPD", "14:00FinPD", "numeroPD", "valoracionPD", 1);*/

        setVistas (view);
        editStartingTime = (EditText) view.findViewById(R.id.franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        SetTime toTime = new SetTime(editEndingTime, container.getContext());

        cargarValores ();

        AppCompatButton enviar_editar = (AppCompatButton) view.findViewById(R.id
        .enviar_editar_donacion);

        enviar_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkCampos();
                    obtenerParametros();
                    // funcion enviar datos
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
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        return view;
        }

    public void setVistas (View view) {

        eNombre = view.findViewById(R.id.nombre_donacion);
        eTelefono = view.findViewById(R.id.telefono_donacion);
        eDireccion = view.findViewById(R.id.direccion_donacion);
        eSolicitudes = view.findViewById(R.id.solicitudes_donacion);
        editStartingTime = view.findViewById(R.id.franja_horaria_inicio_donacion);
        editEndingTime = view.findViewById(R.id.franja_horaria_fin_donacion);
        eDescripcion = view.findViewById(R.id.descripcion_donacion);
    }

    public void cargarValores () {

        eNombre.setText (servicio.getName());
        eTelefono.setText (servicio.getPhoneNumber());
        eDireccion.setText (servicio.getAdress());
        eSolicitudes.setText (servicio.getPlacesLimit());
        editStartingTime.setText (servicio.getStartTime());
        editEndingTime.setText (servicio.getEndTime());
        eDescripcion.setText (servicio.getDescription());
    }

    public void checkCampos () throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkSolicitudesServicio(eSolicitudes.getText().toString());
        checkPlazasAumento(eSolicitudes.getText().toString(), servicio.getPlacesLimit());
        checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros () {

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eSolicitudes.getText().toString());
        param.add(editStartingTime.getText().toString());
        param.add(editEndingTime.getText().toString());
        param.add (eDescripcion.getText().toString());
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
}