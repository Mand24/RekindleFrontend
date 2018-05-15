package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 27/04/2018.
 */

public class EditarPerfilVoluntario extends AbstractFormatChecker{

    private Voluntario voluntario;

    private EditText eNombre;
    private TextView eEmail;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;

    private APIService mAPIService;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_voluntario, container,
                false);


        setVistas(view);

        voluntario = (Voluntario) getActivity().getIntent().getParcelableExtra("Voluntario");

        initializeData(view);


        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    checkCampos(view);
                    obtenerCampos();
                }
                catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendActualizarVoluntario();
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

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view){

        eNombre.setText(voluntario.getName());
        eEmail.setText(voluntario.getMail());
        ePrimer_apellido.setText(voluntario.getSurname1());
        eSegundo_apellido.setText(voluntario.getSurname2());

    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());;
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());

    }

    public void obtenerCampos() {
        voluntario.setName(eNombre.getText().toString());
        voluntario.setSurname1(ePrimer_apellido.getText().toString());
        voluntario.setSurname2(eSegundo_apellido.getText().toString());
    }

    public void sendActualizarVoluntario(){
        mAPIService.actualizarVoluntario(voluntario.getMail(), voluntario).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            tratarResultadoPeticion(true);
                        } else {
                            tratarResultadoPeticion(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "this is an actual network failure"
                                            + " :( inform "
                                            + "the user and "
                                            + "possibly retry", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), "Actualizado correctamente",
                    Toast
                            .LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), VerPerfil.class);
            i.putExtra("tipo", "Volunteer");
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Actualización fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

}
