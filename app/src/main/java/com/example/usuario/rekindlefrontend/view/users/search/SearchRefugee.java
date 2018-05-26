package com.example.usuario.rekindlefrontend.view.users.search;


import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 04/05/2018.
 */

public class SearchRefugee extends AppBaseActivity {

    private EditText eName;
    private EditText eSurname1;
    private EditText eSurname2;
    private EditText eBirthday;
    private Spinner sSex;
    private EditText eCountry;
    private EditText eTown;
    private EditText eEthnic;
    private Spinner sBlood;
    private Spinner sEyes;

    private String eNameString;
    private String eSurname1String;
    private String eSurname2String;
    private String eBirthdayString;
    private String sSexString;
    private String eCountryString;
    private String eTownString;
    private String eEthnicString;
    private String sBloodString;
    private String eEyesString;

    private APIService mAPIService;
    private ArrayList<Refugee> mListRefugees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_refugiado);
        getSupportActionBar().setTitle(R.string.titulo_busqueda);

        setViews();

        AppCompatButton button_send = (AppCompatButton) findViewById(R.id
                .enviar_buscar_personas);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    getParams();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendSearchRefugee();


            }
        });

        SetDate setDate = new SetDate(eBirthday, this);

    }


    public void setViews() {

        eName = findViewById(R.id.nombre_refugiado);
        eSurname1 = findViewById(R.id.p_apellido_refugiado);
        eSurname2 = findViewById(R.id.s_apellido_refugiado);
        eBirthday = findViewById(R.id.nacimiento_refugiado);
        sSex = findViewById(R.id.sexo_refugiado);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSex.setAdapter(adapter_sexo);
        eCountry = findViewById(R.id.procedencia_refugiado);
        eTown = findViewById(R.id.pueblo_refugiado);
        eEthnic = findViewById(R.id.etnia_refugiado);

        sBlood = findViewById(R.id.grupo_sanguineo_refugiado);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sBlood.setAdapter(adapter_gs);

        sEyes = findViewById(R.id.ojos_refugiado);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_color_ojos, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sEyes.setAdapter(adapter_ojos);

        mAPIService = APIUtils.getAPIService();
    }

    public void getParams() {

        eNameString = eName.getText().toString();
        eSurname1String = eSurname1.getText().toString();
        eSurname2String = eSurname2.getText().toString();
        eBirthdayString = eBirthday.getText().toString();
        if (eBirthdayString.equals("")) eBirthdayString = "1890-01-01";
        sSexString = sSex.getSelectedItem().toString();
        eCountryString = eCountry.getText().toString();
        eTownString = eTown.getText().toString();
        eEthnicString = eEthnic.getText().toString();
        sBloodString = sBlood.getSelectedItem().toString();
        eEyesString = sEyes.getSelectedItem().toString();
    }

    public void sendSearchRefugee() {

        mAPIService.buscarRefugiados(
                eNameString,
                eSurname1String,
                eSurname2String,
                eBirthdayString,
                sSexString,
                eCountryString,
                eTownString,
                eEthnicString,
                sBloodString,
                eEyesString, getUser(getApplicationContext()).getMail())
                .enqueue(new Callback<ArrayList<Refugee>>() {

                    @Override
                    public void onResponse(Call<ArrayList<Refugee>> call,
                            Response<ArrayList<Refugee>> response) {
                        System.out.println("url " + call.request().url());

                        if (response.isSuccessful()) {
                            System.out.println("dentro respuesta ok");
                            mListRefugees = response.body();

                            manageResult(true);

                        } else {
                            if (response.body() != null) {
                                System.out.println(
                                        "Resposta: " + response.toString
                                                ());
                            } else {
                                System.out.println("refugiado null");
                            }
                            System.out.println("Mensaje: " + response.message());
                            System.out.println("codi: " + response.code());
                            System.out.println("dentro respuesta failed");
                            manageResult(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Refugee>> call,
                            Throwable t) {
                        System.out.println("url " + call.request().url());

                        manageResult(false);
                    }
                });
    }

    public void manageResult(boolean result) {

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.busqueda_correcta), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), ListRefugee.class);
            i.putParcelableArrayListExtra("mListRefugees", mListRefugees);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.busqueda_fallida), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
