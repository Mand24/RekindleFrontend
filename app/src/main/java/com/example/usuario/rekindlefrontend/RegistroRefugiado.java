package com.example.usuario.rekindlefrontend;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends Fragment {

    public RegistroRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_refugiado, container, false);

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

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id
                .enviar_registro_refugiado);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //comprobar parametros
                try {

                    System.out.println("llamada async");
                    boolean result = new AsyncTaskCall().execute().get();
                    if (result) Toast.makeText(getActivity().getApplicationContext(), "Registro "
                            + "creado!", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getActivity().getApplicationContext(), "Registro "
                            + "failed", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {
            ArrayList<String> param = new ArrayList<String>();
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

            String url = "http://10.4.41.147:8080";
            boolean result = false;
            try {
                System.out.println("background");
                //result = ComunicacionUsuarios.registrarRefugiado(url, param);
                result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
