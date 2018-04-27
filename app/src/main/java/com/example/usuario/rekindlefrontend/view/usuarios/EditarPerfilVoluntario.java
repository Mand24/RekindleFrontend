package com.example.usuario.rekindlefrontend.view.usuarios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.CambiarPassword;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 27/04/2018.
 */

public class EditarPerfilVoluntario extends AbstractFormatChecker{

    private Voluntario voluntario;

    private EditText eNombre;
    private EditText eEmail;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_voluntario, container,
                false);
        try {

            setVistas(view);

            voluntario = new EditarPerfilVoluntario.AsyncTaskCall().execute().get();

            initializeData(view);

        }catch (Exception e){

            e.printStackTrace();
        }

        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    checkCampos(view);
                }
                catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//TODO: llamar para editar el perfil con los nuevos datos
            }

        });

        AppCompatButton b2 = (AppCompatButton) view.findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CambiarPassword.class);
                i.putExtra("Voluntario", voluntario);
                startActivity(i);
            }

        });

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_usuario_perfil);
        eEmail = view.findViewById(R.id.email_usuario_perfil);
        ePrimer_apellido = view.findViewById(R.id.apellido1_usuario_perfil);
        eSegundo_apellido = view.findViewById(R.id.apellido2_usuario_perfil);

    }


    public void initializeData(View view){

        eNombre.setText(voluntario.getName());
        eEmail.setText(voluntario.getMail());
        ePrimer_apellido.setText(voluntario.getSurname1());
        eSegundo_apellido.setText(voluntario.getSurname2());

    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());

    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Voluntario> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Voluntario doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            Voluntario result = new Voluntario();
            try {
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                        (getActivity().getApplicationContext());
                String param = datos.getString("email", "email");
                result = ComunicacionUsuarios.verPerfilVoluntario(url, param);
            } catch (Exception e) {

                e.printStackTrace();
            }

            return result;
        }
    }

}
