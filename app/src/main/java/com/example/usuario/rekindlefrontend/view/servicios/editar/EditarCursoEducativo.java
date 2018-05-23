package com.example.usuario.rekindlefrontend.view.servicios.editar;

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

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Education;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class EditarCursoEducativo extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Education servicio;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText eAmbito;
    private EditText eRequisitos;
    private EditText eHorario;
    private EditText ePlazas;
    private EditText ePrecio;
    private EditText eDescripcion;

    public EditarCursoEducativo () { }

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
            final View view = inflater.inflate (R.layout.fragment_editar_curso, container, false);

            // set : SERVICIO_CURSOEDUCATIVO

            /*servicio = new Education (1111, "nombreCursoPD", "descripPD", "direccionPD",
                "fechaPD",
                    "ambitoPD", "requiPD", "hoarioPD", "plazasPD", "precioPD", "numeroPD",
                    "valoracionPD", 2);*/

            setVistas (view);

            cargarValores ();

            AppCompatButton enviar_editar = (AppCompatButton) view.findViewById(R.id
            .enviar_editar_curso_educativo);

            enviar_editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            checkCampos ();
                            obtenerParametros ();
                            // crida funcion envio de datos
                        } catch (Exception e) {
                            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
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

    public void setVistas (View view) {

        eNombre = view.findViewById(R.id.nombre_curso_educativo);
        eTelefono = view.findViewById(R.id.telefono_curso_educativo);
        eDireccion = view.findViewById(R.id.direccion_curso_educativo);
        eAmbito = view.findViewById(R.id.ambito_curso_educativo);
        eRequisitos = view.findViewById(R.id.requisitos_curso_educativo);
        eHorario = view.findViewById(R.id.horario_curso_educativo);
        ePlazas = view.findViewById(R.id.plazas_curso_educativo);
        ePrecio = view.findViewById(R.id.precio_curso_educativo);
        eDescripcion = view.findViewById(R.id.descripcion_curso_educativo);
    }

    public void cargarValores () {
        eNombre.setText(servicio.getName());
        eTelefono.setText (servicio.getPhoneNumber());
        eDireccion.setText (servicio.getAdress());
        eAmbito.setText(servicio.getAmbit());
        eRequisitos.setText(servicio.getRequirements());
        eHorario.setText(servicio.getSchedule());
        ePlazas.setText(servicio.getPlaces());
        ePrecio.setText(servicio.getPrice());
        eDescripcion.setText (servicio.getDescription());
    }

    public void checkCampos () throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkAmbitoCursoEducativo(eAmbito.getText().toString());
        checkRequisitosServicio(eRequisitos.getText().toString());
        checkHorarioCursoEducativo(eHorario.getText().toString());
        checkPlazasServicio(ePlazas.getText().toString());
        checkPlazasAumento(ePlazas.getText().toString(), servicio.getPlaces());
        checkDescripcionServicio(eDescripcion.getText().toString());
    }

    public void obtenerParametros () {

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (eAmbito.getText().toString());
        param.add (eRequisitos.getText().toString());
        param.add (eHorario.getText().toString());
        param.add (ePlazas.getText().toString());
        param.add (ePrecio.getText().toString());
        param.add (eDescripcion.getText().toString());
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