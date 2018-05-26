package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.EditProfile;

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
    private ImageView photoUser;

    private APIService mAPIService;
    private Volunteer mVolunteer;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ver_perfil_voluntario, container,
                false);

        setVistas(view);

        sendObtenerVoluntario();

        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_voluntario);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditProfile.class);
                i.putExtra("Volunteer", mVolunteer);
                System.out.println("ver"+ mVolunteer.toString());
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
        photoUser = view.findViewById(R.id.foto_perfil_voluntario);

        mAPIService = APIUtils.getAPIService();
    }

    public void sendObtenerVoluntario(){

        /*SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("user", "");
        User user = gson.fromJson(json, User.class);*/

        User user = getUser(getActivity().getApplicationContext());

        System.out.println("tipo app: "+ user.getMail());
        String mail = user.getMail();

        mAPIService.obtenerVoluntario(mail).enqueue(new Callback<Volunteer>() {
            @Override
            public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {
                if (response.isSuccessful()){
                    mVolunteer = response.body();
                    System.out.println("llamadab"+response.body().toString());
                    System.out.println("llamada"+ mVolunteer);
//                    mVolunteer.setServiceType(1);
                    tratarResultadoPeticion(true);
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error al pedir "
                            + "información mVolunteer", Toast
                            .LENGTH_SHORT).show();
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Volunteer> call, Throwable t) {
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

        tipoUsuario.setText("Volunteer");
        nombreUsuario.setText(mVolunteer.getName());
        apellido1.setText(mVolunteer.getSurname1());
        apellido2.setText(mVolunteer.getSurname2());
        emailUsuario.setText(mVolunteer.getMail());
        if(mVolunteer.getPhoto() != null) {
            photoUser.setImageBitmap(mVolunteer.getDecodedPhoto());
        }else{
            photoUser.setImageResource(R.drawable.ic_usuario);
        }

    }

}
