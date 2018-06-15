package com.example.usuario.rekindlefrontend.view.users.register;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.FormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterRefugee extends Fragment {

    private EditText eName;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRPassword;
    private EditText eSurname1;
    private EditText eSurname2;
    private EditText ePhoneNumber;
    private EditText eBirthday;
    private Spinner sSex;
    private EditText eCountry;
    private EditText eTown;
    private EditText eEthnic;
    private Spinner sBlood;
    private Spinner sEyes;
    private EditText eBiography;

    private APIService mAPIService;
    private Refugee mRefugee;
    private FormatChecker fc;

    public RegisterRefugee() {
        // Required empty public constructor
    }

    public Refugee getRefugee() {
        return mRefugee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register_refugee, container, false);

        //establecer las vistas
        setViews(view);

        //init format Checker
        fc = new FormatChecker(getResources());

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id
                .enviar_registro_refugiado);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    checkFields(view);
                    getParams();
                    sendCreateRefugee();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        SetDate setDate = new SetDate(eBirthday, container.getContext());

        return view;
    }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_refugiado);
        eEmail = view.findViewById(R.id.email_refugiado);
        ePassword = view.findViewById(R.id.password_refugiado);
        eRPassword = view.findViewById(R.id.rpassword_refugiado);
        eSurname1 = view.findViewById(R.id.p_apellido_refugiado);
        eSurname2 = view.findViewById(R.id.s_apellido_refugiado);
        ePhoneNumber = view.findViewById(R.id.telefono_refugiado);
        eBirthday = view.findViewById(R.id.nacimiento_refugiado);
        sSex = view.findViewById(R.id.sexo_refugiado);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSex.setAdapter(adapter_sexo);
        eCountry = view.findViewById(R.id.procedencia_refugiado);
        eTown = view.findViewById(R.id.pueblo_refugiado);
        eEthnic = view.findViewById(R.id.etnia_refugiado);

        sBlood = view.findViewById(R.id.grupo_sanguineo_refugiado);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sBlood.setAdapter(adapter_gs);

        sEyes = view.findViewById(R.id.ojos_refugiado);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sEyes.setAdapter(adapter_ojos);

        eBiography = view.findViewById(R.id.biografia_refugiado);

        mAPIService = APIUtils.getAPIService();
    }

    public void checkFields(View view) throws Exception {

        fc.checkName(eName.getText().toString());
        fc.checkEmail(eEmail.getText().toString());
        fc.checkPassword(ePassword.getText().toString(), eRPassword.getText().toString());
        fc.checkSurname1(eSurname1.getText().toString());
        fc.checkSurname2(eSurname2.getText().toString());
        fc.checkPhoneNumber(ePhoneNumber.getText().toString());
        fc.checkCountry(eCountry.getText().toString());
        fc.checkTown(eTown.getText().toString());
        fc.checkEthnic(eEthnic.getText().toString());
        fc.checkBiography(eBiography.getText().toString());
    }

    public void getParams() {

        System.out.println("dentro obtener");
        mRefugee = new Refugee(eEmail.getText().toString(), ePassword.getText().toString(),
                eName.getText().toString(), eSurname1.getText().toString(),
                eSurname2.getText().toString(), null, ePhoneNumber.getText().toString(),
                eBirthday.getText().toString(), sSex.getSelectedItem().toString(),
                eCountry.getText().toString(), eTown.getText().toString(), eEthnic.getText()
                .toString(), sBlood.getSelectedItem().toString(), sEyes.getSelectedItem
                ().toString(), eBiography.getText().toString());
        System.out.println("crear mRefugee");

    }

    public void sendCreateRefugee() {
        System.out.println("dentro send");
        System.out.println(mRefugee.toString());


        mAPIService.createRefugiado(mRefugee).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
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
                    Toast.makeText(getActivity().getApplicationContext(), "this is an actual "
                            + "network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "conversion issue! big "
                            + "problems :(", Toast.LENGTH_SHORT).show();

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
