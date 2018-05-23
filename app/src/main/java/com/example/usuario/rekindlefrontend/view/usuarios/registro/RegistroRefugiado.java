package com.example.usuario.rekindlefrontend.view.usuarios.registro;


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
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends AbstractFormatChecker {

    private EditText eNombre;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRPassword;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;
    private EditText eTelefono;
    private EditText eNacimiento;
    private Spinner sSexo;
    private EditText eProcedencia;
    private EditText ePueblo;
    private EditText eEtnia;
    private Spinner sGrupo_sanguineo;
    private Spinner sOjos;
    private EditText eBiografia;

    private APIService mAPIService;
    private Refugee mRefugee;

    public RegistroRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_refugiado, container, false);

        //establecer las vistas
        setVistas(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id
                .enviar_registro_refugiado);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    System.out.println("dentro try");
                    checkCampos(view);

                    obtenerParametros();
                    System.out.println("final try");
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateRefugiado();
            }
        });

        SetDate setDate = new SetDate(eNacimiento, container.getContext());

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_refugiado);
        eEmail = view.findViewById(R.id.email_refugiado);
        ePassword = view.findViewById(R.id.password_refugiado);
        eRPassword = view.findViewById(R.id.rpassword_refugiado);
        ePrimer_apellido = view.findViewById(R.id.p_apellido_refugiado);
        eSegundo_apellido = view.findViewById(R.id.s_apellido_refugiado);
        eTelefono = view.findViewById(R.id.telefono_refugiado);
        eNacimiento = view.findViewById(R.id.nacimiento_refugiado);
        sSexo = view.findViewById(R.id.sexo_refugiado);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSexo.setAdapter(adapter_sexo);
        eProcedencia = view.findViewById(R.id.procedencia_refugiado);
        ePueblo = view.findViewById(R.id.pueblo_refugiado);
        eEtnia = view.findViewById(R.id.etnia_refugiado);

        sGrupo_sanguineo = view.findViewById(R.id.grupo_sanguineo_refugiado);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sGrupo_sanguineo.setAdapter(adapter_gs);

        sOjos = view.findViewById(R.id.ojos_refugiado);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sOjos.setAdapter(adapter_ojos);

        eBiografia = view.findViewById(R.id.biografia_refugiado);

        mAPIService = APIUtils.getAPIService();
    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPassword(ePassword.getText().toString(), eRPassword.getText().toString
                ());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());
        checkTelefono(eTelefono.getText().toString());
        checkProcedencia(eProcedencia.getText().toString());
        checkPueblo(ePueblo.getText().toString());
        checkEtnia(eEtnia.getText().toString());
        checkBiografia(eBiografia.getText().toString());
    }

    public void obtenerParametros() {

        System.out.println("dentro obtener");
        mRefugee = new Refugee(eEmail.getText().toString(), ePassword.getText().toString(),
                eNombre.getText().toString(), ePrimer_apellido.getText().toString(),
                eSegundo_apellido.getText().toString(), null, eTelefono.getText().toString(),
                eNacimiento.getText().toString(), sSexo.getSelectedItem().toString(),
                eProcedencia.getText().toString(), ePueblo.getText().toString(), eEtnia.getText()
                .toString(), sGrupo_sanguineo.getSelectedItem().toString(), sOjos.getSelectedItem
                ().toString(), eBiografia.getText().toString());
        System.out.println("crear mRefugee");

    }

    public void sendCreateRefugiado() {
        System.out.println("dentro send");
        System.out.println(mRefugee.toString());


        mAPIService.createRefugiado(mRefugee).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
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
                    Toast.makeText(getActivity().getApplicationContext(), "this is an actual "
                            + "network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "conversion issue! big "
                            + "problems :(", Toast.LENGTH_SHORT).show();

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
