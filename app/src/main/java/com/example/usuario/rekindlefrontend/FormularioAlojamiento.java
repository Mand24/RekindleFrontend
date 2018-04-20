package com.example.usuario.rekindlefrontend;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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

    private EditText eDeadline;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    public FormularioAlojamiento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_alojamiento, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_alojamiento);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (setCampos(view)) {
                    try {
                        obtenerParametros();
                        boolean result = new AsyncTaskCall().execute().get();
                        tratarResultadoPeticion(result);
                    }catch (Exception e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {

                }
            }
        });


        eDeadline = view.findViewById (R.id.fecha_limite_alojamiento);
        SetDate setDate = new SetDate(eDeadline, container.getContext());

        return view;
    }

    public boolean letras (String texto)
    {
        Pattern patron = Pattern.compile ("^[a-zA-Z]+$");
        Matcher valid  = patron.matcher  (texto);
        return  valid.matches ();
    }

    public boolean numeros (String texto)
    {
        Pattern patron = Pattern.compile ("^[0-9]+$");
        Matcher valid  = patron.matcher  (texto);
        return valid.matches ();
    }

    public boolean fecha_valida (String fecha)
    {
        Pattern patron = Pattern.compile ("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        Matcher valid  = patron.matcher  (fecha);
        return valid.matches ();
    }

    public boolean setCampos (View view) {

        EditText container_data;
        Context context = getActivity().getApplicationContext();
        String  texto;

        // control nombre

        container_data = view.findViewById (R.id.nombre_alojamiento);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
            Toast.makeText (context, "Nombre obligatorio", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 50) {
            Toast.makeText(context, "Nombre es demasiado largo, máximo 50 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "El nombre solo puede contener letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { nombre = texto; }

        // control correo

        container_data = view.findViewById (R.id.correo_alojamiento);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0 ) {
            Toast.makeText(context, "Email obligatorio", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 30) {
            Toast.makeText(context, "Email demasiado largo, máximo 30 caracteres", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher (texto).matches
                        ()) {
            Toast.makeText(context, "Formato de email no valido", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { correo = texto; }

        // control telefono

        container_data = view.findViewById (R.id.
                telefono_alojamiento);
        texto = container_data.getText ().toString ();
        if (texto.length () == 0) {
            Toast.makeText(context, "Teléfono obligatorio", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!numeros (texto) && texto.length () > 0) {
            Toast.makeText(context, "Teléfono solo puede contener dígitos", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 50) {
            Toast.makeText(context, "Teléfono demasiado largo, máximo 50 números", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { telefono = texto; }

        // control direccion

        container_data = view.findViewById (R.id.direccion_alojamiento);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
            Toast.makeText (context, "Dirección obligatoria", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 50) {
            Toast.makeText(context, "Dirección demasiada larga, máximo 50 caracteres", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { direccion = texto; }

        // control solicitudes

        container_data = view.findViewById (R.id.
                solicitudes_alojamiento);
        texto = container_data.getText ().toString ();

        if (!numeros (texto) && texto.length () > 0) {
            Toast.makeText(context, "El límite de solicitudes debe ser un número", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { solicitudes = texto; }


        /*if (!fecha_valida (texto) && texto.length () > 0) {
            Toast.makeText(context, "Formato fecha incorrecto; formato correcto = dd-mm-aaaa", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { fecha_limite = texto; }*/

        // control descripción

        container_data = view.findViewById (R.id.
                descripcion_alojamiento);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
            Toast.makeText (context, "Descripción obligatoria", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (texto.length () > 300) {
            Toast.makeText(context, "Descripción es demasiada larga, máximo 50 letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "La descripción solo puede contener letras", Toast
                    .LENGTH_SHORT).show ();
            return false;
        }
        else { descripcion = texto; }

        return true;
    }

    public void obtenerParametros(){

        param = new ArrayList<String>();

        param.add (nombre);
        param.add (correo);
        param.add (telefono);
        param.add (direccion);
        param.add (solicitudes);
        param.add (eDeadline.getText().toString());
        param.add (descripcion);

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.servicio_alojamiento_fallido), Toast.LENGTH_SHORT).show();
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
