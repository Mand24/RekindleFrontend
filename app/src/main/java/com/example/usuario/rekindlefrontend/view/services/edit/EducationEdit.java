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
import com.example.usuario.rekindlefrontend.data.entity.service.Education;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.Consistency;
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

public class EducationEdit extends Fragment {

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

    private APIService mAPIService;
    private FormatChecker fc;

    public EducationEdit() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_education, container, false);

        servicio = (Education) getArguments().getSerializable("Education");

        setViews(view);

        //init format Checker
        fc = new FormatChecker(getResources());

        initializeFields();

        AppCompatButton sendEdit = (AppCompatButton) view.findViewById(R.id
                .enviar_editar_curso_educativo);

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

        eAdress = view.findViewById(R.id.direccion_curso_educativo);
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

        eName = view.findViewById(R.id.nombre_curso_educativo);
        ePhoneNumber = view.findViewById(R.id.telefono_curso_educativo);
        eAdress = view.findViewById(R.id.direccion_curso_educativo);
        eAmbit = view.findViewById(R.id.ambito_curso_educativo);
        eRequirements = view.findViewById(R.id.requisitos_curso_educativo);
        eSchedule = view.findViewById(R.id.horario_curso_educativo);
        ePlacesLimit = view.findViewById(R.id.plazas_curso_educativo);
        ePrice = view.findViewById(R.id.precio_curso_educativo);
        eDescription = view.findViewById(R.id.descripcion_curso_educativo);

        mAPIService = APIUtils.getAPIService();
    }

    public void initializeFields() {
        eName.setText(servicio.getName());
        ePhoneNumber.setText(servicio.getPhoneNumber());
        eAdress.setText(servicio.getAdress());
        eAmbit.setText(servicio.getAmbit());
        eRequirements.setText(servicio.getRequirements());
        eSchedule.setText(servicio.getSchedule());
        ePlacesLimit.setText(servicio.getPlacesLimit());
        ePrice.setText(servicio.getPrice());
        eDescription.setText(servicio.getDescription());
    }

    public void checkFields() throws Exception {

        fc.checkServiceName(eName.getText().toString());
        fc.checkServicePhoneNumber(ePhoneNumber.getText().toString());
        fc.checkServiceAmbit(eAmbit.getText().toString());
        fc.checkServiceRequirements(eRequirements.getText().toString());
        fc.checkServiceSchedule(eSchedule.getText().toString());
        fc.checkServicePlaces(ePlacesLimit.getText().toString());
        fc.checkServiceIncreasePlaces(ePlacesLimit.getText().toString(), servicio.getPlacesLimit());
        fc.checkServiceDescription(eDescription.getText().toString());
    }

    public void getParams() {

        servicio.setName(eName.getText().toString());
        servicio.setPhoneNumber(ePhoneNumber.getText().toString());
        servicio.setAdress(eAdress.getText().toString());
        servicio.setAmbit(eAmbit.getText().toString());
        servicio.setRequirements(eRequirements.getText().toString());
        servicio.setSchedule(eSchedule.getText().toString());
        servicio.setPlacesLimit(ePlacesLimit.getText().toString());
        servicio.setPrice(ePrice.getText().toString());
        servicio.setDescription(eDescription.getText().toString());
    }

    public void sendUpdateService() {
        mAPIService.editarCurso(Consistency.getUser(getActivity().getApplicationContext())
                .getApiKey(), servicio.getId(), servicio)
                .enqueue(new
                                                                                        Callback<Void>() {
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