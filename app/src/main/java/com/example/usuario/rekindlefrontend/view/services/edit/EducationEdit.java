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
import com.example.usuario.rekindlefrontend.data.entity.service.Education;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class EducationEdit extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Education servicio;
    private EditText eAdress;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eName;
    private EditText eEmail;
    private EditText ePhoneNumber;
    private EditText eAmbit;
    private EditText eRequirements;
    private EditText eSchedule;
    private EditText ePlacesLimit;
    private EditText ePrice;
    private EditText eDescription;

    public EducationEdit() { }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
            final View view = inflater.inflate (R.layout.fragment_editar_curso, container, false);

            // set : SERVICIO_CURSOEDUCATIVO

            /*service = new Education (1111, "nombreCursoPD", "descripPD", "direccionPD",
                "fechaPD",
                    "ambitoPD", "requiPD", "hoarioPD", "plazasPD", "precioPD", "numeroPD",
                    "valoracionPD", 2);*/

            setViews(view);

            initializeFields();

            AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
            .enviar_editar_curso_educativo);

            sendEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            checkFields();
                            getParams();
                            // crida funcion envio de datos
                        } catch (Exception e) {
                            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            });

            eAdress = view.findViewById(R.id.direccion_curso_educativo);
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

        eName = view.findViewById(R.id.nombre_curso_educativo);
        ePhoneNumber = view.findViewById(R.id.telefono_curso_educativo);
        eAdress = view.findViewById(R.id.direccion_curso_educativo);
        eAmbit = view.findViewById(R.id.ambito_curso_educativo);
        eRequirements = view.findViewById(R.id.requisitos_curso_educativo);
        eSchedule = view.findViewById(R.id.horario_curso_educativo);
        ePlacesLimit = view.findViewById(R.id.plazas_curso_educativo);
        ePrice = view.findViewById(R.id.precio_curso_educativo);
        eDescription = view.findViewById(R.id.descripcion_curso_educativo);
    }

    public void initializeFields() {
        eName.setText(servicio.getName());
        ePhoneNumber.setText (servicio.getPhoneNumber());
        eAdress.setText (servicio.getAdress());
        eAmbit.setText(servicio.getAmbit());
        eRequirements.setText(servicio.getRequirements());
        eSchedule.setText(servicio.getSchedule());
        ePlacesLimit.setText(servicio.getPlacesLimit());
        ePrice.setText(servicio.getPrice());
        eDescription.setText (servicio.getDescription());
    }

    public void checkFields() throws Exception {

        checkServiceName(eName.getText().toString());
        checkServicePhoneNumber(ePhoneNumber.getText().toString());
        checkServiceAmbit(eAmbit.getText().toString());
        checkServiceRequirements(eRequirements.getText().toString());
        checkServiceSchedule(eSchedule.getText().toString());
        checkServicePlaces(ePlacesLimit.getText().toString());
        checkServiceIncreasePlaces(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        checkServiceDescription(eDescription.getText().toString());
    }

    public void getParams() {

        param = new ArrayList<String>();

        param.add (eName.getText().toString());
        param.add (ePhoneNumber.getText().toString());
        param.add (eAdress.getText().toString());
        param.add (eAmbit.getText().toString());
        param.add (eRequirements.getText().toString());
        param.add (eSchedule.getText().toString());
        param.add (ePlacesLimit.getText().toString());
        param.add (ePrice.getText().toString());
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