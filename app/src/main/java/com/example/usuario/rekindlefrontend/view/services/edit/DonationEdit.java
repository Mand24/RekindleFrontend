package com.example.usuario.rekindlefrontend.view.services.edit;

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

import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetTime;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class DonationEdit extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Donation servicio;

    private EditText editStartingTime, editEndingTime;
    private EditText eAdress;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText eName;
    private EditText eEmail;
    private EditText ePhoneNumber;
    private EditText ePlacesLimit;
    private EditText eDescription;

    public DonationEdit() {

        }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
        savedInstanceState) {
        final View view = inflater.inflate (R.layout.fragment_editar_donacion, container, false);

        // set : SERVICIO_DONACION

        /*service = new Donation (1111, "nombrePD", "descrpcionPD", "direccionPD", "solicitudesPD",
                "13:00IncioPD", "14:00FinPD", "numeroPD", "valoracionPD", 1);*/

        setViews(view);
        editStartingTime = (EditText) view.findViewById(R.id.franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        SetTime toTime = new SetTime(editEndingTime, container.getContext());

        initializeFields();

        AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
        .enviar_editar_donacion);

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

        eAdress = view.findViewById(R.id.direccion_donacion);
        eAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete
                    .MODE_OVERLAY).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        });

        return view;
        }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_donacion);
        ePhoneNumber = view.findViewById(R.id.telefono_donacion);
        eAdress = view.findViewById(R.id.direccion_donacion);
        ePlacesLimit = view.findViewById(R.id.solicitudes_donacion);
        editStartingTime = view.findViewById(R.id.franja_horaria_inicio_donacion);
        editEndingTime = view.findViewById(R.id.franja_horaria_fin_donacion);
        eDescription = view.findViewById(R.id.descripcion_donacion);
    }

    public void initializeFields() {

        eName.setText (servicio.getName());
        ePhoneNumber.setText (servicio.getPhoneNumber());
        eAdress.setText (servicio.getAdress());
        ePlacesLimit.setText (servicio.getPlacesLimit());
        editStartingTime.setText (servicio.getStartTime());
        editEndingTime.setText (servicio.getEndTime());
        eDescription.setText (servicio.getDescription());
    }

    public void checkFields() throws Exception {

        checkServiceName(eName.getText().toString());
        checkServicePhoneNumber(ePhoneNumber.getText().toString());
        checkServicePlaces(ePlacesLimit.getText().toString());
        checkServiceIncreasePlaces(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        checkServiceDescription(eDescription.getText().toString());

    }

    public void getParams() {

        param = new ArrayList<String>();

        param.add (eName.getText().toString());
        param.add (ePhoneNumber.getText().toString());
        param.add (eAdress.getText().toString());
        param.add (ePlacesLimit.getText().toString());
        param.add(editStartingTime.getText().toString());
        param.add(editEndingTime.getText().toString());
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
                Log.i("==================", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}