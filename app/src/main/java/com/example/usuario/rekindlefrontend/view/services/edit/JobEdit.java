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
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class JobEdit extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Job servicio;
    private EditText eAdress;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eName;
    private EditText eEmail;
    private EditText ePhoneNumber;
    private EditText eCharge;
    private EditText eRequirements;
    private EditText eHoursDay;
    private EditText eHoursWeek;
    private EditText eContractDuration;
    private EditText eSalary;
    private EditText ePlacesLimit;
    private EditText eDescription;

    public JobEdit() {}

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
            final View view = inflater.inflate (R.layout.fragment_editar_empleo, container, false);

            // set : SERVICIO_EMPLEO

            /**/
            //TODO: que esté bien
            /*service = new Job (111, "nombrePD", "descripPD", "direccionPD",
                "puestoPD",
                    "requisitosPD", "joranadaPD", "horasSemanaPD", "duracionPD", "plazasPD",
                    "sueldoPD", "numeroPD", "valoracionPD", 3);*/

            setViews(view);

            initializeFields();

            AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
            .enviar_editar_oferta_empleo);

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

            eAdress = view.findViewById(R.id.direccion_oferta_empleo);
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

        eName = view.findViewById(R.id.nombre_oferta_empleo);
        ePhoneNumber = view.findViewById(R.id.telefono_oferta_empleo);
        eAdress = view.findViewById(R.id.direccion_oferta_empleo);
        eCharge = view.findViewById(R.id.puesto_oferta_empleo);
        eRequirements = view.findViewById(R.id.requisitos_oferta_empleo);
        eHoursDay = view.findViewById(R.id.jornada_oferta_empleo);
        eHoursWeek = view.findViewById(R.id.horas_semanales_oferta_empleo);
        eContractDuration = view.findViewById(R.id.duracion_oferta_empleo);
        eSalary = view.findViewById(R.id.sueldo_oferta_empleo);
        ePlacesLimit = view.findViewById(R.id.plazas_oferta_empleo);
        eDescription = view.findViewById(R.id.descripcion_oferta_empleo);
    }

    public void initializeFields() {

        eName.setText (servicio.getName());
        ePhoneNumber.setText (servicio.getPhoneNumber());
        eAdress.setText (servicio.getAdress());
        eCharge.setText (servicio.getCharge());
        eRequirements.setText(servicio.getRequirements());
        eHoursDay.setText(servicio.getHoursDay());
        eHoursWeek.setText(servicio.getHoursWeek());
        eContractDuration.setText(servicio.getContractDuration());
        eSalary.setText(servicio.getSalary());
        eCharge.setText(servicio.getCharge());
        ePlacesLimit.setText(servicio.getPlacesLimit());
        eDescription.setText (servicio.getDescription());
    }

    public void checkFields() throws Exception {

        checkServiceName(eName.getText().toString());
        checkServicePhoneNumber(ePhoneNumber.getText().toString());
        checkServiceCharge(eCharge.getText().toString());
        checkServiceRequirements(eRequirements.getText().toString());
        checkServiceSalary(eSalary.getText().toString());
        checkServicePlaces(ePlacesLimit.getText().toString());
        checkServiceIncreasePlaces(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        checkServiceDescription(eDescription.getText().toString());
    }

    public void getParams() {

        param = new ArrayList<String>();

        param.add (eName.getText().toString());
        param.add (ePhoneNumber.getText().toString());
        param.add (eAdress.getText().toString());
        param.add (eCharge.getText().toString());
        param.add (eRequirements.getText().toString());
        param.add (eHoursDay.getText().toString());
        param.add (eHoursWeek.getText().toString());
        param.add (eContractDuration.getText().toString());
        param.add (eSalary.getText().toString());
        param.add (ePlacesLimit.getText().toString());
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