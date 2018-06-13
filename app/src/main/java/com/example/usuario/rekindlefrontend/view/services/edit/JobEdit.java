package com.example.usuario.rekindlefrontend.view.services.edit;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.Fragment;
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
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.FormatChecker;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesVolunteer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobEdit extends Fragment {

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

    private APIService mAPIService;
    private FormatChecker fc;

    public JobEdit() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_job, container, false);

        servicio = (Job) getArguments().getSerializable("Job");

        setViews(view);

        //init format Checker
        fc = new FormatChecker(getResources());

        initializeFields();

        AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
                .enviar_editar_oferta_empleo);

        sendEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkFields();
                    getParams();
                    sendUpdateService();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        eAdress = view.findViewById(R.id.direccion_oferta_empleo);
        eAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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

        mAPIService = APIUtils.getAPIService();
    }

    public void initializeFields() {

        eName.setText(servicio.getName());
        ePhoneNumber.setText(servicio.getPhoneNumber());
        eAdress.setText(servicio.getAdress());
        eCharge.setText(servicio.getCharge());
        eRequirements.setText(servicio.getRequirements());
        eHoursDay.setText(servicio.getHoursDay());
        eHoursWeek.setText(servicio.getHoursWeek());
        eContractDuration.setText(servicio.getContractDuration());
        eSalary.setText(servicio.getSalary());
        eCharge.setText(servicio.getCharge());
        ePlacesLimit.setText(servicio.getPlacesLimit());
        eDescription.setText(servicio.getDescription());
    }

    public void checkFields() throws Exception {

        fc.checkServiceName(eName.getText().toString());
        fc.checkServicePhoneNumber(ePhoneNumber.getText().toString());
        fc.checkServiceCharge(eCharge.getText().toString());
        fc.checkServiceRequirements(eRequirements.getText().toString());
        fc.checkServiceSalary(eSalary.getText().toString());
        fc.checkServicePlaces(ePlacesLimit.getText().toString());
        fc.checkServiceIncreasePlaces(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        fc.checkServiceDescription(eDescription.getText().toString());
    }

    public void getParams() {

        servicio.setName(eName.getText().toString());
        servicio.setPhoneNumber(ePhoneNumber.getText().toString());
        servicio.setAdress(eAdress.getText().toString());
        servicio.setCharge(eCharge.getText().toString());
        servicio.setRequirements(eRequirements.getText().toString());
        servicio.setHoursDay(eHoursDay.getText().toString());
        servicio.setHoursWeek(eHoursWeek.getText().toString());
        servicio.setContractDuration(eContractDuration.getText().toString());
        servicio.setSalary(eSalary.getText().toString());
        servicio.setPlacesLimit(ePlacesLimit.getText().toString());
        servicio.setDescription(eDescription.getText().toString());
    }

    public void sendUpdateService() {
        mAPIService.editarEmpleo(servicio.getId(), servicio).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    manageResult(true);
                } else {
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                manageResult(false);
            }
        });
    }

    public void manageResult(boolean result) {
        if (result) {
            Toast.makeText(getActivity().getApplicationContext(), "Actualizado correctamente",
                    Toast
                            .LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MyServicesVolunteer.class);
            startActivity(i);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Actualización fallida", Toast
                    .LENGTH_SHORT).show();
        }
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