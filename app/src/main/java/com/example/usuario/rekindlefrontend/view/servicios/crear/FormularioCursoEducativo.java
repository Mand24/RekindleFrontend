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
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
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
public class FormularioCursoEducativo extends AbstractFormatChecker {

    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eNombre;
    private EditText eTelefono;
    private EditText eAmbito;
    private EditText eRequisitos;
    private EditText eHorario;
    private EditText ePlazas;
    private EditText ePrecio;
    private EditText eDescripcion;
    private CursoEducativo mCursoEducativo;
    private APIService mAPIService;

    public FormularioCursoEducativo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_curso_educativo, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_curso_educativo);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    checkCampos(view);
                    obtenerParametros();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCrearEducacion();
            }
        });

        eDireccion = view.findViewById(R.id.direccion_curso_educativo);
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

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_curso_educativo);
        eTelefono = view.findViewById(R.id.telefono_curso_educativo);
        eDireccion = view.findViewById(R.id.direccion_curso_educativo);
        eAmbito = view.findViewById(R.id.ambito_curso_educativo);
        eRequisitos = view.findViewById(R.id.requisitos_curso_educativo);
        eHorario = view.findViewById(R.id.horario_curso_educativo);
        ePlazas = view.findViewById(R.id.plazas_curso_educativo);
        ePrecio = view.findViewById(R.id.precio_curso_educativo);
        eDescripcion = view.findViewById(R.id.descripcion_curso_educativo);

        mAPIService = APIUtils.getAPIService();

    }

    public void checkCampos(View view) throws Exception {//FALTA CHECK PRECIO!!!

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkAmbitoCursoEducativo(eAmbito.getText().toString());
        checkRequisitosServicio(eRequisitos.getText().toString());
        checkHorarioCursoEducativo(eHorario.getText().toString());
        checkPlazasServicio(ePlazas.getText().toString());
        checkDescripcionServicio(eDescripcion.getText().toString());

    }

    public void obtenerParametros(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);

        mCursoEducativo = new CursoEducativo(0, usuario.getMail(), eNombre.getText().toString(),
                eDescripcion.getText().toString(), eDireccion.getText().toString(), eAmbito
                .getText().toString(), eRequisitos.getText().toString(), eHorario.getText()
                .toString(), ePlazas.getText().toString(), ePrecio.getText().toString(),
                eTelefono.getText().toString());

    }

    public void sendCrearEducacion(){
        mAPIService.crearEducacion(mCursoEducativo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    tratarResultadoPeticion(true);
                }
                else {
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
                    .string.curso_educativo_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            i.putExtra("tipo", 1);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.curso_educativo_fallido), Toast.LENGTH_SHORT).show();
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
