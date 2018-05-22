package com.example.usuario.rekindlefrontend.view.servicios.editar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
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
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.Calendar;

public class EditarAlojamiento extends AbstractFormatChecker {

    private Alojamiento servicio;
    private ArrayList<String> param;

    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eDireccion;
    private EditText eSolicitudes;
    private EditText eDeadline;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText eDescripcion;

    public EditarAlojamiento () {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
        final View view = inflater.inflate (R.layout.fragment_editar_alojamiento, container, false);

        // set : SERVICIO_ALOJAMIENTO
        //TODO: new Alojamiento
        //servicio =  new Alojamiento (12345, "nombrePD", "descrPD", "direccionPD", "soliciPD",
          //      "21-03-2018PD", "123456789PD", "valoracionPD", 0);

        setVistas (view);
        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        SetDate setDate = new SetDate(eDeadline, container.getContext());

        cargarValores ();

        AppCompatButton enviar_editar = (AppCompatButton) view.findViewById(R.id
                .enviar_editar_alojamiento);

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

        eDireccion = view.findViewById(R.id.direccion_alojamiento);
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

    public void setVistas (View view) {

        eNombre = view.findViewById(R.id.nombre_alojamiento);
      //TODO:  eEmail = view.findViewById(R.id.correo_alojamiento);
        eTelefono = view.findViewById(R.id.telefono_alojamiento);
        eDireccion = view.findViewById(R.id.direccion_alojamiento);
        eSolicitudes = view.findViewById(R.id.solicitudes_alojamiento);
        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        eDescripcion = view.findViewById(R.id.descripcion_alojamiento);
    }

    public void cargarValores () {

        eNombre.setText (servicio.getNombre ());
        eTelefono.setText (servicio.getNumero ());
        eDireccion.setText (servicio.getDireccion ());
        eSolicitudes.setText(servicio.getLimiteSolicitudes ());
        eDeadline.setText (servicio.getFecha ());
        eDescripcion.setText (servicio.getDescripcion ());
    }

    public void checkCampos () throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkSolicitudesServicio(eSolicitudes.getText().toString());
        checkPlazasAumento(eSolicitudes.getText().toString(), servicio.getLimiteSolicitudes());
        checkDescripcionServicio(eDescripcion.getText().toString());
    }

    public void obtenerParametros () {

        new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eSolicitudes.getText().toString());
        param.add (eDeadline.getText().toString());
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
