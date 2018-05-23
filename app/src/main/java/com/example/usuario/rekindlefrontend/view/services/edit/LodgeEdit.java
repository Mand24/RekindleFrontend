package com.example.usuario.rekindlefrontend.view.services.edit;

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
import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.Calendar;

public class LodgeEdit extends AbstractFormatChecker {

    private Lodge servicio;
    private ArrayList<String> param;

    private EditText eName;
    private EditText eEmail;
    private EditText ePhoneNumber;
    private EditText eAdress;
    private EditText ePlacesLimit;
    private EditText eDeadline;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText eDescription;

    public LodgeEdit() {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
        final View view = inflater.inflate (R.layout.fragment_editar_alojamiento, container, false);

        setViews(view);
        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        SetDate setDate = new SetDate(eDeadline, container.getContext());

        initializeFields();

        AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
                .enviar_editar_alojamiento);

        sendEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkFields();
                    getParams();
                    // funcion enviar datos
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        eAdress = view.findViewById(R.id.direccion_alojamiento);
        eAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete
                            .MODE_OVERLAY).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }catch (GooglePlayServicesRepairableException e) {
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        });

        return view;
    }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_alojamiento);
      //TODO:  eEmail = view.findViewById(R.id.correo_alojamiento);
        ePhoneNumber = view.findViewById(R.id.telefono_alojamiento);
        eAdress = view.findViewById(R.id.direccion_alojamiento);
        ePlacesLimit = view.findViewById(R.id.solicitudes_alojamiento);
        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        eDescription = view.findViewById(R.id.descripcion_alojamiento);
    }

    public void initializeFields() {

        eName.setText (servicio.getName());
        ePhoneNumber.setText (servicio.getPhoneNumber());
        eAdress.setText (servicio.getAdress());
        ePlacesLimit.setText(servicio.getPlacesLimit());
        eDeadline.setText (servicio.getDateLimit());
        eDescription.setText (servicio.getDescription());
    }

    public void checkFields() throws Exception {

        checkNombreServicio(eName.getText().toString());
        checkTelefonoServicio(ePhoneNumber.getText().toString());
        checkSolicitudesServicio(ePlacesLimit.getText().toString());
        checkPlazasAumento(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        checkDescripcionServicio(eDescription.getText().toString());
    }

    public void getParams() {

        new ArrayList<String>();

        param.add (eName.getText().toString());
        param.add (ePhoneNumber.getText().toString());
        param.add (eAdress.getText().toString());
        param.add (ePlacesLimit.getText().toString());
        param.add (eDeadline.getText().toString());
        param.add (eDescription.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i("==================", "Place: " + place.getName());
                eAdress.setText(place.getAddress());
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
