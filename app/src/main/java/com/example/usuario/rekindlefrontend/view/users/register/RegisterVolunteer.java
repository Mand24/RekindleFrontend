package com.example.usuario.rekindlefrontend.view.users.register;


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
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
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
public class RegisterVolunteer extends AbstractFormatChecker {

    private EditText eName;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRPassword;
    private EditText eSurname1;
    private EditText eSurname2;

    private APIService mAPIService;
    private Volunteer mVolunteer;

    public RegisterVolunteer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_register_volunteer, container,
                false);

        setViews(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(
                R.id.enviar_registro_voluntario);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    checkFields(view);
                    getParams();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateVolunteer();
            }
        });

        return view;
    }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_voluntario);
        eEmail = view.findViewById(R.id.email_voluntario);
        ePassword = view.findViewById(R.id.password_voluntario);
        eRPassword = view.findViewById(R.id.rpassword_voluntario);
        eSurname1 = view.findViewById(R.id.p_apellido_voluntario);
        eSurname2 = view.findViewById(R.id.s_apellido_voluntario);

        mAPIService = APIUtils.getAPIService();

    }

    public void checkFields(View view) throws Exception {

        checkName(eName.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPassword(ePassword.getText().toString(), eRPassword.getText().toString
                ());
        checkSurname1(eSurname1.getText().toString());
        checkSurname2(eSurname2.getText().toString());

    }

    public void getParams() {

        mVolunteer = new Volunteer(eEmail.getText().toString(), ePassword.getText().toString(),
                eName.getText().toString(), eSurname1.getText().toString(),
                eSurname2.getText().toString(), null);

    }

    public void sendCreateVolunteer() {

        mAPIService.createVoluntario(mVolunteer).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    System.out.println(mVolunteer.toString());
                    System.out.println("dentro respuesta ok");
                    manageResult(true);
                } else {
                    System.out.println("Mensaje: " + response.message());
                    System.out.println("codi: " + response.code());
                    System.out.println("dentro respuesta failed");
                    manageResult(false);
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
                manageResult(false);
            }
        });
    }

    public void manageResult(boolean result) {

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
