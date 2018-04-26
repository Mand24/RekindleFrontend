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
import android.widget.TextView;


import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.entity.Usuario;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Voluntario;

public class VerPerfilVoluntario extends Fragment {

    private Voluntario voluntario;

    private TextView tipoUsuario;
    private TextView nombreUsuario;
    private TextView apellido1;
    private TextView apellido2;
    private TextView emailUsuario;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ver_perfil_voluntario, container,
                false);
        try {
            voluntario = new AsyncTaskCall().execute().get();

        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setVistas(view);

        llenarTextViews();

//TODO: EditarPerfilRefugiado
        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_voluntario);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditarPerfil.class);
                i.putExtra("Voluntario", voluntario);
                startActivity(i);
            }

        });

        return view;

    }

    public void setVistas(View view) {

        tipoUsuario = view.findViewById(R.id.tipo_usuario_perfil_voluntario);
        nombreUsuario = view.findViewById(R.id.nombre_usuario_perfil_voluntario);
        apellido1 = view.findViewById(R.id.apellido1_usuario_perfil_voluntario);
        apellido2 = view.findViewById(R.id.apellido2_usuario_perfil_voluntario);
        emailUsuario = view.findViewById(R.id.email_usuario_perfil_voluntario);
    }

    public void llenarTextViews(){

        tipoUsuario.setText("Refugiado");
        nombreUsuario.setText(voluntario.getName());
        apellido1.setText(voluntario.getSurname1());
        apellido2.setText(voluntario.getSurname2());
        emailUsuario.setText(voluntario.getMail());


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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }


}
