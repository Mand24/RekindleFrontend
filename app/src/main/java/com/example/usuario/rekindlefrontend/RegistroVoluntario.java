package com.example.usuario.rekindlefrontend;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroVoluntario extends Fragment {


    public RegistroVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_voluntario, container, false);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //comprobar parametros
                try {
                    System.out.println("registro voluntario");
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

            String url = "http://10.4.41.147:8080";
            boolean result = false;
            try {
                result = Comunicacion.registrarVoluntario(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
