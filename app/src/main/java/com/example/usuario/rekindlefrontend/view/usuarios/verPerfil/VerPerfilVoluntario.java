package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

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
import android.widget.Toast;


import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.EditarPerfil;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPerfilVoluntario extends Fragment {

    private TextView tipoUsuario;
    private TextView nombreUsuario;
    private TextView apellido1;
    private TextView apellido2;
    private TextView emailUsuario;

    private APIService mAPIService;
    private Voluntario voluntario;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ver_perfil_voluntario, container,
                false);

        setVistas(view);

        /*try {
            voluntario = new AsyncTaskCall().execute().get();

        }catch (Exception e){

            e.printStackTrace();
        }*/

        sendObtenerVoluntario();

        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_voluntario);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditarPerfil.class);
                i.putExtra("tipo", 1);
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

        mAPIService = APIUtils.getAPIService();
    }

    public void sendObtenerVoluntario(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        System.out.println("tipo app: "+ usuario.getMail());
        String mail = usuario.getMail();

        mAPIService.obtenerVoluntario(mail).enqueue(new Callback<Voluntario>() {
            @Override
            public void onResponse(Call<Voluntario> call, Response<Voluntario> response) {
                if (response.isSuccessful()){
                    voluntario = response.body();
                    voluntario.setTipo(1);
                    tratarResultadoPeticion(true);
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error al pedir "
                            + "información voluntario", Toast
                            .LENGTH_SHORT).show();
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Voluntario> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(), "this is an actual network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            llenarTextViews();

            Toast.makeText(getActivity().getApplicationContext(), "Ver perfil correctamente",
                    Toast
                            .LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Ver perfil fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

    public void llenarTextViews(){

        tipoUsuario.setText("Voluntario");
        nombreUsuario.setText(voluntario.getName());
        apellido1.setText(voluntario.getSurname1());
        apellido2.setText(voluntario.getSurname2());
        emailUsuario.setText(voluntario.getMail());

    }

    /*private class AsyncTaskCall extends AsyncTask<String, Void, Voluntario> {

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
    }*/


}
