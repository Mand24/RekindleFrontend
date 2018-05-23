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
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class EditarOfertaEmpleo extends AbstractFormatChecker {

    private ArrayList<String> param;
    private Job servicio;
    private EditText eDireccion;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eNombre;
    private EditText eEmail;
    private EditText eTelefono;
    private EditText ePuesto;
    private EditText eRequisitos;
    private EditText eJornada;
    private EditText eHoras;
    private EditText eDuracion;
    private EditText eSueldo;
    private EditText ePlazas;
    private EditText eDescripcion;

    public EditarOfertaEmpleo () {}

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState) {
            final View view = inflater.inflate (R.layout.fragment_editar_empleo, container, false);

            // set : SERVICIO_EMPLEO

            /**/
            //TODO: que esté bien
            /*servicio = new Job (111, "nombrePD", "descripPD", "direccionPD",
                "puestoPD",
                    "requisitosPD", "joranadaPD", "horasSemanaPD", "duracionPD", "plazasPD",
                    "sueldoPD", "numeroPD", "valoracionPD", 3);*/

            setVistas (view);

            cargarValores ();

            AppCompatButton enviar_editar = (AppCompatButton) view.findViewById(R.id
            .enviar_editar_oferta_empleo);

            enviar_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        try {
                            checkCampos();
                            obtenerParametros();
                            // crida funcion envio de datos
                        } catch (Exception e) {
                            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                }
            });

            eDireccion = view.findViewById(R.id.direccion_oferta_empleo);
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

        eNombre = view.findViewById(R.id.nombre_oferta_empleo);
        eTelefono = view.findViewById(R.id.telefono_oferta_empleo);
        eDireccion = view.findViewById(R.id.direccion_oferta_empleo);
        ePuesto = view.findViewById(R.id.puesto_oferta_empleo);
        eRequisitos = view.findViewById(R.id.requisitos_oferta_empleo);
        eJornada = view.findViewById(R.id.jornada_oferta_empleo);
        eHoras = view.findViewById(R.id.horas_semanales_oferta_empleo);
        eDuracion = view.findViewById(R.id.duracion_oferta_empleo);
        eSueldo = view.findViewById(R.id.sueldo_oferta_empleo);
        ePlazas = view.findViewById(R.id.plazas_oferta_empleo);
        eDescripcion = view.findViewById(R.id.descripcion_oferta_empleo);
    }

    public void cargarValores () {

        eNombre.setText (servicio.getName());
        eTelefono.setText (servicio.getPhoneNumber());
        eDireccion.setText (servicio.getAdress());
        ePuesto.setText (servicio.getCharge());
        eRequisitos.setText(servicio.getRequirements());
        eJornada.setText(servicio.getHoursDay());
        eHoras.setText(servicio.getHoursWeek());
        eDuracion.setText(servicio.getContractDuration());
        eSueldo.setText(servicio.getSalary());
        ePuesto.setText(servicio.getCharge());
        ePlazas.setText(servicio.getPlaces());
        eDescripcion.setText (servicio.getDescription());
    }

    public void checkCampos () throws Exception {

        checkNombreServicio(eNombre.getText().toString());
        checkTelefonoServicio(eTelefono.getText().toString());
        checkPuestoOfertaEmpleo(ePuesto.getText().toString());
        checkRequisitosServicio(eRequisitos.getText().toString());
        checkSueldoOfertaEmpleo(eSueldo.getText().toString());
        checkPlazasServicio(ePlazas.getText().toString());
        checkPlazasAumento(ePlazas.getText().toString(), servicio.getPlaces());
        checkDescripcionServicio(eDescripcion.getText().toString());
    }

    public void obtenerParametros () {

        param = new ArrayList<String>();

        param.add (eNombre.getText().toString());
        param.add (eTelefono.getText().toString());
        param.add (eDireccion.getText().toString());
        param.add (ePuesto.getText().toString());
        param.add (eRequisitos.getText().toString());
        param.add (eJornada.getText().toString());
        param.add (eHoras.getText().toString());
        param.add (eDuracion.getText().toString());
        param.add (eSueldo.getText().toString());
        param.add (ePlazas.getText().toString());
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