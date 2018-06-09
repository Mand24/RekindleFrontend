package com.example.usuario.rekindlefrontend.view.services.create;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

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
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EducationForm extends AbstractFormatChecker {

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eName;
    private EditText ePhoneNumber;
    private EditText eAdress;
    private EditText eAmbit;
    private EditText eRequirements;
    private EditText eSchedule;
    private EditText ePlacesLimit;
    private EditText ePrice;
    private EditText eDescription;
    private Education mEducation;
    private APIService mAPIService;
    private User user;

    public EducationForm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_education_form, container,
                false);

        user = getUser(getActivity().getApplicationContext());

        //establecer las vistas
        setViews(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_formulario_curso_educativo);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    checkFields(view);
                    getParams();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateEducation();
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

    public void checkFields(View view) throws Exception {

        checkServiceName(eName.getText().toString());
        checkServicePhoneNumber(ePhoneNumber.getText().toString());
        checkServiceAmbit(eAmbit.getText().toString());
        checkServiceRequirements(eRequirements.getText().toString());
        checkServiceSchedule(eSchedule.getText().toString());
        checkServicePlaces(ePlacesLimit.getText().toString());
        checkServiceDescription(eDescription.getText().toString());

    }

    public void getParams() {

        mEducation = new Education(0, user.getMail(), eName.getText().toString(),
                eDescription.getText().toString(), eAdress.getText().toString(), eAmbit
                .getText().toString(), eRequirements.getText().toString(), eSchedule.getText()
                .toString(), ePlacesLimit.getText().toString(), ePrice.getText().toString(),
                ePhoneNumber.getText().toString());

    }

    public void sendCreateEducation() {
        mAPIService.crearEducacion(user.getApiKey(), mEducation).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    manageResult(true);
                } else {
                    System.out.println("codi " + response.code());
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

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.curso_educativo_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MainMenu.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.curso_educativo_fallido), Toast.LENGTH_SHORT).show();
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
