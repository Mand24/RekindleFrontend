package com.example.usuario.rekindlefrontend.view.servicios;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionServicios;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioAlojamiento extends Fragment {

    private ArrayList<String> param;

    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String solicitudes;
    private String fecha_limite;
    private String descripcion;

    private EditText eDireccion;
    private EditText eDeadline;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    public FormularioAlojamiento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_alojamiento, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_formulario_alojamiento);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (setCampos(view)) {
                    try {
                        obtenerParametros();
                        boolean result = new AsyncTaskCall().execute().get();
                        tratarResultadoPeticion(result);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {

                }
            }
        });

        eDireccion = view.findViewById(R.id.direccion_alojamiento);
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


        eDeadline = view.findViewById(R.id.fecha_limite_alojamiento);
        SetDate setDate = new SetDate(eDeadline, container.getContext());

        return view;
    }

    public boolean letras(String texto) {
        Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public boolean numeros(String texto) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public boolean fecha_valida(String fecha) {
        Pattern patron = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        Matcher valid = patron.matcher(fecha);
        return valid.matches();
    }

    public boolean setCampos(View view) {

        EditText container_data;
        Context context = getActivity().getApplicationContext();
        String texto;

        // control nombre

        container_data = view.findViewById(R.id.nombre_alojamiento);
        texto = container_data.getText().toString();

        if (texto.length() == 0) {
            Toast.makeText(context, "Nombre obligatorio", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (texto.length() > 50) {
            Toast.makeText(context, "Nombre es demasiado largo, máximo 50 letras", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (!letras(texto)) {
            Toast.makeText(context, "El nombre solo puede contener letras", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            nombre = texto;
        }

        // control correo

        container_data = view.findViewById(R.id.correo_alojamiento);
        texto = container_data.getText().toString();

        if (texto.length() == 0) {
            Toast.makeText(context, "Email obligatorio", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (texto.length() > 30) {
            Toast.makeText(context, "Email demasiado largo, máximo 30 caracteres", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(texto).matches
                        ()) {
            Toast.makeText(context, "Formato de email no valido", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            correo = texto;
        }

        // control telefono

        container_data = view.findViewById(R.id.
                telefono_alojamiento);
        texto = container_data.getText().toString();
        if (texto.length() == 0) {
            Toast.makeText(context, "Teléfono obligatorio", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (!numeros(texto) && texto.length() > 0) {
            Toast.makeText(context, "Teléfono solo puede contener dígitos", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (texto.length() > 50) {
            Toast.makeText(context, "Teléfono demasiado largo, máximo 50 números", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            telefono = texto;
        }

        // control direccion

        container_data = view.findViewById(R.id.direccion_alojamiento);
        texto = container_data.getText().toString();

        if (texto.length() == 0) {
            Toast.makeText(context, "Dirección obligatoria", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (texto.length() > 50) {
            Toast.makeText(context, "Dirección demasiada larga, máximo 50 caracteres", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            direccion = texto;
        }

        // control solicitudes

        container_data = view.findViewById(R.id.
                solicitudes_alojamiento);
        texto = container_data.getText().toString();

        if (!numeros(texto) && texto.length() > 0) {
            Toast.makeText(context, "El límite de solicitudes debe ser un número", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            solicitudes = texto;
        }


        /*if (!fecha_valida (texto) && texto.length () > 0) {
            Toast.makeText(context, "Formato fecha incorrecto; formato correcto = dd-mm-aaaa", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { fecha_limite = texto; }*/

        // control descripción

        container_data = view.findViewById(R.id.
                descripcion_alojamiento);
        texto = container_data.getText().toString();

        if (texto.length() == 0) {
            Toast.makeText(context, "Descripción obligatoria", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (texto.length() > 300) {
            Toast.makeText(context, "Descripción es demasiada larga, máximo 50 letras", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else if (!letras(texto)) {
            Toast.makeText(context, "La descripción solo puede contener letras", Toast
                    .LENGTH_SHORT).show();
            return false;
        } else {
            descripcion = texto;
        }

        return true;
    }

    public void obtenerParametros() {

        param = new ArrayList<String>();

        param.add(nombre);
        param.add(correo);
        param.add(telefono);
        param.add(direccion);
        param.add(solicitudes);
        param.add(eDeadline.getText().toString());
        param.add(descripcion);

    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
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
                Log.i("Create ride", "Place: " + place.getName());
                eDireccion.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i("Create ride", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            boolean result = false;
            try {
                result = ComunicacionServicios.crearAlojamiento(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }

}
