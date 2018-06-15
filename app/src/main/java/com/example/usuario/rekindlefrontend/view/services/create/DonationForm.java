package com.example.usuario.rekindlefrontend.view.services.create;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.FormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.utils.SetTime;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationForm extends Fragment {


    private EditText editStartingTime, editEndingTime;
    private EditText eAdress;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText eName;
    private EditText ePhoneNumber;
    private EditText ePlacesLimit;
    private EditText eDescription;
    private EditText eExpiresOn;

    private Donation mDonation;
    private APIService mAPIService;
    private FormatChecker fc;

    private User user;

    public DonationForm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_donation_form, container,
                false);

        user = getUser(getActivity().getApplicationContext());

        //establecer las vistas
        setViews(view);

        eExpiresOn = (EditText) view.findViewById(R.id.donation_expires_on_date);
        SetDate expiresOn = new SetDate(eExpiresOn, container.getContext());

        //init format Checker
        fc = new FormatChecker(getResources());

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_formulario_donacion);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    checkFields(view);
                    getParams();
                    sendCreateDonation();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        eAdress = view.findViewById(R.id.direccion_donacion);
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

        editStartingTime = (EditText) view.findViewById(R.id
                .franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        SetTime toTime = new SetTime(editEndingTime, container.getContext());
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

        mAPIService = APIUtils.getAPIService();

    }

    public void checkFields(View view) throws Exception {

        fc.checkServiceName(eName.getText().toString());
        fc.checkServicePhoneNumber(ePhoneNumber.getText().toString());
        fc.checkServicePlaces(ePlacesLimit.getText().toString());
        fc.checkServiceDescription(eDescription.getText().toString());

    }

    public void getParams() {
        User user = getUser(getActivity().getApplicationContext());
        Geocoder geo = new Geocoder(getActivity().getApplicationContext());
        List<Address> addresses = null;
        Address locationAddress = null;
        try {
            addresses = geo.getFromLocationName(eAdress.getText().toString(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null && addresses.size() > 0) {
            locationAddress = addresses.get(0);
        }

        if (locationAddress != null) {
            Double latitude = locationAddress.getLatitude();
            Double longitude = locationAddress.getLongitude();
            mDonation = new Donation(0, user.getMail(), eName.getText().toString(),
                    eDescription.getText().toString(), eAdress.getText().toString(), latitude,
                    longitude, ePlacesLimit
                    .getText().toString(), editStartingTime.getText().toString(), editEndingTime
                    .getText().toString(), ePhoneNumber.getText().toString(), false, eExpiresOn
                    .getText().toString());
        } else {
            eAdress.setError(getString(R.string.location_error));
        }

    }

    public void sendCreateDonation() {
        mAPIService.crearDonacion(user.getApiKey(), mDonation).enqueue(new Callback<Void>() {
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
                    .string.donacion_creada_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MainMenu.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.donacion_fallida), Toast.LENGTH_SHORT).show();
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
