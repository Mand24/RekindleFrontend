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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends Fragment {

    private ArrayList<String> param;

    private String nombre;
    private String email;
    private String pass;
    private String primer_apellido;
    private String segundo_apellido;
    private String telefono;
    private String nacimiento;
    private String sexo;
    private String procedencia;
    private String pueblo;
    private String etnia;
    private String grupo_sanguineo;
    private String ojos;

    private EditText eNacimiento;

    public RegistroRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_refugiado, container, false);

        //establecer parametros a los spinners
        setSpinners(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id
                .enviar_registro_refugiado);
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
                    //el mensaje de error lo saca la función comprobarCampos
                }
            }
        });

        eNacimiento = view.findViewById(R.id.nacimiento_refugiado);
        SetDate setDate = new SetDate(eNacimiento, container.getContext());

        return view;
    }

    public void setSpinners(View view){

        Spinner spinner = (Spinner) view.findViewById(R.id.sexo_refugiado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.grupo_sanguineo_refugiado);

        adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.ojos_refugiado);

        adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public boolean letras (String texto)
    {
        Pattern patron = Pattern.compile ("^[a-zA-Z]+$");
        Matcher valid  = patron.matcher  (texto);
        return valid.matches ();
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

    public boolean setCampos (View view)
    {
        EditText container_data;
        final Context context = getActivity ().getApplicationContext ();
        Spinner  spinner;
        String   texto;
        String   texto_aux;

        // no control: sexo, grupo sanguineo, ojos

        spinner         = view   .findViewById    (R.id.grupo_sanguineo_refugiado);
        grupo_sanguineo = spinner.getSelectedItem ().toString ();
        spinner         = view   .findViewById    (R.id.ojos_refugiado);
        ojos            = spinner.getSelectedItem ().toString ();
        spinner         = view   .findViewById    (R.id.sexo_refugiado);
        sexo            = spinner.getSelectedItem ().toString ();

        // control nombre

        container_data = view.findViewById (R.id.nombre_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
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
        else { nombre = texto; }

        // control email

        container_data = view.findViewById (R.id.email_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0 ) {
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
        else { email = texto; }

        // control password

        container_data = view.findViewById (R.id.password_refugiado);
        texto = container_data.getText ().toString ();

        container_data = view.findViewById (R.id.rpassword_refugiado);
        texto_aux  = container_data.getText ().toString ();

        if (texto.length () == 0) {
            Toast.makeText (context, "Contraseña obligatoria", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 15) {
            Toast.makeText(context, "Contraseña demasiada larga, máximo 15 caracteres", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () < 4) {
            Toast.makeText(context, "Contraseña demasiada corta, mínimo 4 caracteres", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!texto.equals (texto_aux)) {
            Toast.makeText(context, "Las contraseñas no coinciden", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { pass = texto; }

        // control primer apellido

        container_data = view.findViewById (R.id.
                p_apellido_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () == 0) {
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
        else { primer_apellido = texto; }

        // control segundo apellido

        container_data = view.findViewById (R.id.
                s_apellido_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () > 20) {
            Toast.makeText(context, "Segundo apellido demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El segundo apellido solo puede contener letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { segundo_apellido = texto; }

        // control telefono

        container_data = view.findViewById (R.id.
                telefono_refugiado);
        texto = container_data.getText ().toString ();

        if (!numeros (texto) && texto.length () > 0) {
            Toast.makeText(context, "Teléfono solo puede contener dígitos", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (texto.length () > 40) {
            Toast.makeText(context, "Teléfono demasiado largo, máximo 50 números", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { telefono = texto; }

        // control nacimiento

        eNacimiento = view.findViewById (R.id.
                nacimiento_refugiado);

        // control procedencia

        container_data = view.findViewById (R.id.
                procedencia_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () > 20) {
            Toast.makeText(context, "País de origen demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre del país de origen solo puede contener letras",
                    Toast
                            .LENGTH_LONG).show ();
            return false;
        }
        else { procedencia = texto; }

        // control pueblo

        container_data = view.findViewById (R.id.
                pueblo_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () > 40) {
            Toast.makeText(context, "Nombre del pueblo demasiado largo, máximo 40 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre del pueblo solo puede contener letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else { pueblo = texto; }

        // control etnia

        container_data = view.findViewById (R.id.
                etnia_refugiado);
        texto = container_data.getText ().toString ();

        if (texto.length () > 20) {
            Toast.makeText(context, "Nombre de la etnia demasiado largo, máximo 20 letras", Toast
                    .LENGTH_LONG).show ();
            return false;
        }
        else if (!letras (texto) && texto.length () > 0) {
            Toast.makeText(context, "El nombre de la etnia de origen solo puede contener letras",
                    Toast.LENGTH_LONG).show ();
            return false;
        }
        else { etnia = texto; }

        return true;
    }

    public void obtenerParametros(){

        /*param = new ArrayList<String>();
        param.add("manelico@gmail.com");
        param.add("sergimanel");
        param.add("pedrito");
        param.add("garcia");
        param.add("monserrate");
        param.add("53322863");
        param.add("1995-05-05");
        param.add("Femenino");
        param.add("Senegal");
        param.add("town");
        param.add("senegalo");
        param.add("AB+");
        param.add("Gris");*/

        param.add (email);
        param.add (pass);
        param.add (nombre);
        param.add (primer_apellido);
        param.add (segundo_apellido);
        param.add (telefono);
        param.add (eNacimiento.getText().toString());
        param.add (sexo);
        param.add (procedencia);
        param.add (pueblo);
        param.add (etnia);
        param.add(grupo_sanguineo);
        param.add(ojos);

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
                result = ComunicacionUsuarios.registrarRefugiado(url, param);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
