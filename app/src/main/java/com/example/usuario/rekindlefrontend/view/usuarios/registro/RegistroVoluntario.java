package com.example.usuario.rekindlefrontend.view.usuarios.registro;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroVoluntario extends AbstractFormatChecker {

    private EditText eNombre;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRPassword;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;

    private APIService mAPIService;
    private Voluntario voluntario;

    public RegistroVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_voluntario, container,
                false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    checkCampos(view);
                    obtenerParametros();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateVoluntario();
            }
        });

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_voluntario);
        eEmail = view.findViewById(R.id.email_voluntario);
        ePassword = view.findViewById(R.id.password_voluntario);
        eRPassword = view.findViewById(R.id.rpassword_voluntario);
        ePrimer_apellido = view.findViewById(R.id.p_apellido_voluntario);
        eSegundo_apellido = view.findViewById(R.id.s_apellido_voluntario);

        mAPIService = APIUtils.getAPIService();

    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPassword(ePassword.getText().toString(), eRPassword.getText().toString
                ());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());

    }

    public void obtenerParametros() {

        voluntario = new Voluntario(eEmail.getText().toString(), ePassword.getText().toString(),
                eNombre.getText().toString(), ePrimer_apellido.getText().toString(),
                eSegundo_apellido.getText().toString());

    }

    public void sendCreateVoluntario() {

        mAPIService.createVoluntario(voluntario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    System.out.println(voluntario.toString());
                    System.out.println("dentro respuesta ok");
                    tratarResultadoPeticion(true);
//                    showResponse(response.body().toString());
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    System.out.println("Mensaje: " + response.message());
                    System.out.println("codi: " + response.code());
                    System.out.println("dentro respuesta failed");
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
//                Log.e(TAG, "Unable to submit post to API.");
                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
                tratarResultadoPeticion(false);
            }
        });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registrado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), Login.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registro_fallido), Toast.LENGTH_SHORT).show();
        }
    }
}
