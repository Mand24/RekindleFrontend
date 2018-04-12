package com.example.usuario.rekindlefrontend;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroVoluntario extends Fragment {

    private ArrayList<String> param;

    private String nombre;
    private String email;
    private String pass;
    private String primer_apellido;
    private String segundo_apellido;

    public RegistroVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_voluntario, container,
                false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (setCampos (view)) {
                    try {
                        obtenerParametros();
                        boolean result = new AsyncTaskCall().execute().get();
                        tratarResultadoPeticion(result);
                    }catch (Exception e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    // el mensaje de error lo saca la funcion set campos
                }
            }
        });

        return view;
    }

    public boolean letras (String texto)
    {
        Pattern patron = Pattern.compile ("[a-zA-Z]");
        Matcher valid  = patron.matcher  (texto);
        if(valid.matches ()) return true;
        else                 return false;
    }

    public boolean setCampos (View view)
    {
        EditText container_data;
        Context context = getActivity().getApplicationContext();
        String   texto;

        container_data = view.findViewById (R.id.nombre_voluntario);
        texto = container_data.getText ().toString ();
        nombre = texto;

        /*if (texto.length () == 0) {
            Toast.makeText (context, "Nombre obligatorio", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 20) {
            Toast.makeText(context, "Nombre es demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "El nombre solo puede contener letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { nombre = texto; }*/

        // control email

        container_data = view.findViewById (R.id.email_voluntario);
        texto = container_data.getText ().toString ();
        email = texto;

        /*if (texto.length () == 0 ) {
            Toast.makeText(context, "Email obligatorio", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 30) {
            Toast.makeText(context, "Email demasiado largo, máximo 30 caracteres", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher (texto).matches
                        ()) {
            Toast.makeText(context, "Formato de email no valido", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { email = texto; }*/

        // control password

        container_data = view.findViewById (R.id.password_voluntario);
        texto = container_data.getText ().toString ();
        pass = texto;

        /*if (texto.length () == 0) {
            Toast.makeText (context, "Contraseña obligatoria", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 15) {
            Toast.makeText(context, "Contraseña demasiada larga, máximo 15 caracteres", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () < 15) {
            Toast.makeText(context, "Contraseña demasiada corta, mínimo 4 caracteres", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { pass = texto; }*/

        // control primer apellido

        container_data = view.findViewById (R.id.
                p_apellido_voluntario);
        texto = container_data.getText ().toString ();
        primer_apellido = texto;

        /*if (texto.length () == 0) {
            Toast.makeText (context, "Primer apellido obligatorio", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 20) {
            Toast.makeText(context, "Primer apellido demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "El primer apellido solo puede contener letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { primer_apellido = texto; }*/

        // control segundo apellido

        container_data = view.findViewById (R.id.
                s_apellido_voluntario);
        texto = container_data.getText ().toString ();
        segundo_apellido = texto;

        /*if (texto.length () > 20) {
            Toast.makeText(context, "Segundo apellido demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto)) {
            Toast.makeText(context, "El segundo apellido solo puede contener letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { segundo_apellido = texto; }*/

        return true;
    }

    public void obtenerParametros(){

        param = new ArrayList<String>();
        param.add("refa@gmail.com");
        param.add("sergimanel");
        param.add("refa");
        param.add("garcia");
        param.add("monserrate");

        /*param.add(email);
        param.add(pass);
        param.add(nombre);
        param.add(primer_apellido);
        param.add(segundo_apellido);*/

        System.out.println("nombre: " + nombre);

    }

    public void tratarResultadoPeticion(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registrado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), PantallaInicio.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.registro_fallido), Toast.LENGTH_SHORT).show();
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            boolean result = false;
            try {
                result = ComunicacionUsuarios.registrarVoluntario(url, param);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
