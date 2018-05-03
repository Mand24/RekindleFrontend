package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

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
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
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

public class EditarPerfilRefugiado extends AbstractFormatChecker{

    private Refugiado refugiado;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;

    private EditText eNombre;
    private EditText eEmail;
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

    private APIService mAPIService;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_refugiado, container,
                false);

        setVistas(view);

        refugiado = (Refugiado) getActivity().getIntent().getSerializableExtra("Refugiado");

        initializeData(view);


        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    checkCampos(view);
                }
                catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                sendActualizarRefugiado();

            }

        });

        AppCompatButton b2 = (AppCompatButton) view.findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CambiarPassword.class);
                i.putExtra("Refugiado", refugiado);
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
        eTelefono = view.findViewById(R.id.telefono_usuario_perfil);
        eNacimiento = view.findViewById(R.id.naciminento_usuario_perfil);
        sSexo = view.findViewById(R.id.sexo_usuario_perfil);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSexo.setAdapter(adapter_sexo);
        eProcedencia = view.findViewById(R.id.pais_usuario_perfil);
        ePueblo = view.findViewById(R.id.pueblo_usuario_perfil);
        eEtnia = view.findViewById(R.id.etnia_usuario_perfil);

        sGrupo_sanguineo = view.findViewById(R.id.sangre_usuario_perfil);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sGrupo_sanguineo.setAdapter(adapter_gs);

        sOjos = view.findViewById(R.id.ojos_usuario_perfil);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sOjos.setAdapter(adapter_ojos);

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view){

        eNombre.setText(refugiado.getName());
        eEmail.setText(refugiado.getMail());
        ePrimer_apellido.setText(refugiado.getSurname1());
        eSegundo_apellido.setText(refugiado.getSurname2());
        eTelefono.setText(refugiado.getPhoneNumber());
        eNacimiento.setText(refugiado.getBirthDate());

        int selectionPosition = adapter1.getPosition(refugiado.getSex());
        sSexo.setSelection(selectionPosition);

        eProcedencia.setText(refugiado.getCountry());
        ePueblo.setText(refugiado.getTown());
        eEtnia.setText(refugiado.getEthnic());

        selectionPosition = adapter2.getPosition(refugiado.getBloodType());
        sGrupo_sanguineo.setSelection(selectionPosition);

        selectionPosition = adapter3.getPosition(refugiado.getEyeColor());
        sOjos.setSelection(selectionPosition);
    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkEmail(eEmail.getText().toString());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());
        checkTelefono(eTelefono.getText().toString());
        checkProcedencia(eProcedencia.getText().toString());
        checkPueblo(ePueblo.getText().toString());
        checkEtnia(eEtnia.getText().toString());
    }

    public void sendActualizarRefugiado() {
        mAPIService.actualizarRefugiado(refugiado.getMail(), refugiado).enqueue(
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
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Actualización fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

    /*private class AsyncTaskCall extends AsyncTask<String, Void, Refugiado> {

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
//TODO: Es esto correcto?
                result = ComunicacionUsuarios.verPerfilRefugiado(url, param);
            } catch (Exception e) {

                e.printStackTrace();
            }

            return result;
        }
    }*/

}
