package com.example.usuario.rekindlefrontend.view.servicios.crear;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionServicios;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetTime;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioDonacion extends AbstractFormatChecker {


    private EditText editStartingTime, editEndingTime;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText eNombre;
    private EditText eTelefono;
    private EditText eSolicitudes;
    private EditText eDescripcion;

    private Donacion mDonacion;
    private APIService mAPIService;

    public FormularioDonacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_donacion, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_donacion);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    checkCampos(view);
                    obtenerParametros();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCrearDonacion();
            }
        });

        eDireccion = view.findViewById(R.id.direccion_donacion);
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

        editStartingTime = (EditText) view.findViewById(R.id
                .franja_horaria_inicio_donacion);
        SetTime fromTime = new SetTime(editStartingTime, container.getContext());
        SetTime toTime = new SetTime(editEndingTime, container.getContext());
        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_donacion);
        eTelefono = view.findViewById(R.id.telefono_donacion);
        eDireccion = view.findViewById(R.id.direccion_donacion);
        eSolicitudes = view.findViewById(R.id.solicitudes_donacion);
        editStartingTime = view.findViewById(R.id.franja_horaria_inicio_donacion);
        editEndingTime = view.findViewById(R.id.franja_horaria_fin_donacion);
        eDescripcion = view.findViewById(R.id.descripcion_donacion);

        mAPIService = APIUtils.getAPIService();

    }

    public void checkCampos(View view) throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkSolicitudesServicio(eSolicitudes.getText().toString());
        checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);

        mDonacion = new Donacion(0, usuario.getMail(), eNombre.getText().toString(),
                eDescripcion.getText().toString(), eDireccion.getText().toString(), eSolicitudes
                .getText().toString(), editStartingTime.getText().toString(), editEndingTime
                .getText().toString(), eTelefono.getText().toString());

    }

    public void sendCrearDonacion(){
        mAPIService.crearDonacion(mDonacion).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    tratarResultadoPeticion(true);
                }else {
                    System.out.println("codi "+response.code());
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tratarResultadoPeticion(false);

            }
        });
    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.donacion_creada_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            i.putExtra("tipo", "Volunteer");
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.donacion_fallida), Toast.LENGTH_SHORT).show();
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
