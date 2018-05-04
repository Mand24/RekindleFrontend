package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.EditarPerfil;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPerfilRefugiado extends Fragment {

    private TextView tipoUsuario;
    private TextView nombreUsuario;
    private TextView apellido1;
    private TextView apellido2;
    private TextView emailUsuario;
    private TextView telefonoUsuario;
    private TextView nacimientoUsuario;
    private TextView sexoUsuario;
    private TextView paisUsuario;
    private TextView puebloUsuario;
    private TextView etniaUsuario;
    private TextView sangreUsuario;
    private TextView ojosUsuario;

    private APIService mAPIService;
    private Refugiado refugiado;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ver_perfil_refugiado, container,
                false);

        setVistas(view);

        /*try {
            refugiado = new AsyncTaskCall().execute().get();

        }catch (Exception e){

            e.printStackTrace();
        }*/

        sendObtenerRefugiado();


        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_refugiado);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditarPerfil.class);
                i.putExtra("tipo", 0);
                i.putExtra("Refugiado", refugiado);
                startActivity(i);
            }

        });

        return view;

    }

    public void setVistas(View view) {

        tipoUsuario = view.findViewById(R.id.tipo_usuario_perfil_refugiado);
        nombreUsuario = view.findViewById(R.id.nombre_usuario_perfil_refugiado);
        apellido1 = view.findViewById(R.id.apellido1_usuario_perfil_refugiado);
        apellido2 = view.findViewById(R.id.apellido2_usuario_perfil_refugiado);
        emailUsuario = view.findViewById(R.id.email_usuario_perfil_refugiado);
        telefonoUsuario = view.findViewById(R.id.telefono_usuario_perfil_refugiado);
        nacimientoUsuario = view.findViewById(R.id.naciminento_usuario_perfil_refugiado);
        sexoUsuario = view.findViewById(R.id.sexo_usuario_perfil_refugiado);
        paisUsuario = view.findViewById(R.id.pais_usuario_perfil_refugiado);
        puebloUsuario = view.findViewById(R.id.pueblo_usuario_perfil_refugiado);
        etniaUsuario = view.findViewById(R.id.etnia_usuario_perfil_refugiado);
        sangreUsuario = view.findViewById(R.id.sangre_usuario_perfil_refugiado);
        ojosUsuario = view.findViewById(R.id.ojos_usuario_perfil_refugiado);

        mAPIService = APIUtils.getAPIService();
    }

    public void sendObtenerRefugiado(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        System.out.println("tipo app: "+ usuario.getMail());
        String mail = usuario.getMail();

        mAPIService.obtenerRefugiado(mail).enqueue(new Callback<Refugiado>() {
            @Override
            public void onResponse(Call<Refugiado> call, Response<Refugiado> response) {
                if (response.isSuccessful()){
                    System.out.println("dentro respuesta");
                    if (response.body() != null) System.out.println("dentro respuesta ok");
                    refugiado = response.body();
                    refugiado.setTipo(0);
                    tratarResultadoPeticion(true);
                }
                else {
                    System.out.println("refugiado null");
                    System.out.println("Mensaje: "+response.message());
                    System.out.println("codi: "+response.code());
                    System.out.println("dentro respuesta failed");
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Refugiado> call, Throwable t) {
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

            Toast.makeText(getActivity().getApplicationContext(), "ver perfil correctamente",
                    Toast
                    .LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "ver perfil fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

    public void llenarTextViews(){

        tipoUsuario.setText("Refugiado");
        nombreUsuario.setText(refugiado.getName());
        apellido1.setText(refugiado.getSurname1());
        apellido2.setText(refugiado.getSurname2());
        emailUsuario.setText(refugiado.getMail());
        telefonoUsuario.setText(refugiado.getPhoneNumber());
        nacimientoUsuario.setText(refugiado.getBirthDate());
        sexoUsuario.setText(refugiado.getSex());
        paisUsuario.setText(refugiado.getCountry());
        puebloUsuario.setText(refugiado.getTown());
        etniaUsuario.setText(refugiado.getEthnic());
        sangreUsuario.setText(refugiado.getBloodType());
        ojosUsuario.setText(refugiado.getEyeColor());

    }

   /* private class AsyncTaskCall extends AsyncTask<String, Void, Refugiado> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Refugiado doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            Refugiado result = new Refugiado();
            try {
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
                String param = datos.getString("email", "email");
                result = ComunicacionUsuarios.verPerfilRefugiado(url, param);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }*/


}
