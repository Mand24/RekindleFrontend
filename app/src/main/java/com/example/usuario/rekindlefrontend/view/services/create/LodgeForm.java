package com.example.usuario.rekindlefrontend.view.services.create;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.DatePickerDialog;
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
import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LodgeForm extends AbstractFormatChecker {

    private EditText eName;
    private EditText ePhoneNumber;
    private EditText eAdress;
    private EditText ePlacesLimit;
    private EditText eDeadline;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText eDescription;
    private Lodge mLodge;
    private APIService mAPIService;

    public LodgeForm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_alojamiento, container,
                false);

        setViews(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_formulario_alojamiento);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    checkFields(view);
                    getParams();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateLodge();
            }
        });

        eAdress = view.findViewById(R.id.direccion_alojamiento);
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


        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        SetDate setDate = new SetDate(eDeadline, container.getContext());

        return view;
    }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_alojamiento);
        ePhoneNumber = view.findViewById(R.id.telefono_alojamiento);
        eAdress = view.findViewById(R.id.direccion_alojamiento);
        ePlacesLimit = view.findViewById(R.id.solicitudes_alojamiento);
        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        eDescription = view.findViewById(R.id.descripcion_alojamiento);

        mAPIService = APIUtils.getAPIService();
    }

    public void checkFields(View view) throws Exception {

        checkServiceName(eName.getText().toString());
        checkServicePhoneNumber(ePhoneNumber.getText().toString());
        checkServicePlaces(ePlacesLimit.getText().toString());
        checkServiceDescription(eDescription.getText().toString());

    }

    public void getParams() {

        User user = getUser(getActivity().getApplicationContext());

        mLodge = new Lodge(0, user.getMail(), eName.getText().toString(),
                eDescription.getText().toString(), eAdress.getText().toString(), ePlacesLimit
                .getText().toString(), eDeadline.getText().toString(), ePhoneNumber.getText()
                .toString());


    }

    public void sendCreateLodge() {
        mAPIService.crearAlojamiento(mLodge).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("llamada " + call.toString());
                if (response.isSuccessful()) {
                    manageResult(true);
                } else {
                    System.out.println("codi " + response.code());
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void manageResult(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MainMenu.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_fallido), Toast.LENGTH_SHORT).show();
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
