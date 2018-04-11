package com.example.usuario.rekindlefrontend;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends Fragment {

    private ArrayList<String> param;

    public RegistroRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                if (comprobarCampos()) {
                    try {
                        obtenerParametros(view);
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

    public boolean comprobarCampos(){
        return true;
    }

    public void obtenerParametros(View view){

        param = new ArrayList<String>();
        param.add("pedrito");
        param.add("pedrito@gmail.com");
        param.add("sergimanel");
        param.add("garcia");
        param.add("monserrate");
        param.add("53322863");
        param.add("1995-05-05");
        param.add("Femenino");
        param.add("Senegal");
        param.add("town");
        param.add("senegalo");
        param.add("AB+");
        param.add("Gris");

        /*EditText editText = (EditText) view.findViewById(R.id.nombre_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.email_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.password_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.p_apellido_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.s_apellido_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.telefono_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.nacimiento_refugiado);
        param.add(editText.getText().toString());

        Spinner spinner = (Spinner) view.findViewById(R.id.sexo_refugiado);
        param.add(spinner.getSelectedItem().toString());

        editText = (EditText) view.findViewById(R.id.procedencia_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.pueblo_refugiado);
        param.add(editText.getText().toString());

        editText = (EditText) view.findViewById(R.id.etnia_refugiado);
        param.add(editText.getText().toString());

        spinner = (Spinner) view.findViewById(R.id.grupo_sanguineo_refugiado);
        param.add(spinner.getSelectedItem().toString());

        spinner = (Spinner) view.findViewById(R.id.ojos_refugiado);
        param.add(spinner.getSelectedItem().toString());*/

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
