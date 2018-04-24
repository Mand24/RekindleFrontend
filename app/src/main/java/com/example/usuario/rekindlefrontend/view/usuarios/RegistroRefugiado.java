package com.example.usuario.rekindlefrontend.view.usuarios;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.comunicacion.ComunicacionUsuarios;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.SetDate;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends AbstractFormatChecker {

    private ArrayList<String> param;

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
                    checkCampos(view);
                    obtenerParametros();
                    boolean result = new AsyncTaskCall().execute().get();
                    tratarResultadoPeticion(result);
                    //tratarResultadoPeticion(true);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

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
    }

    public void obtenerParametros() {

        /*param = new ArrayList<String>();
        param.add("manelico@gmail.com");
        param.add("sergimanel");
        param.add("pedrito");
        param.add("garcia");
        param.add("monserrate");
        param.add("53322863");
        param.add("1995-05-05");
        param.add("Femenino");
        param.add("Senegal");
        param.add("town");
        param.add("senegalo");
        param.add("AB+");
        param.add("Gris");*/

        param.add(eEmail.getText().toString());
        param.add(ePassword.getText().toString());
        param.add(eNombre.getText().toString());
        param.add(ePrimer_apellido.getText().toString());
        param.add(eSegundo_apellido.getText().toString());
        param.add(eTelefono.getText().toString());
        param.add(eNacimiento.getText().toString());
        param.add(sSexo.getSelectedItem().toString());
        param.add(eProcedencia.getText().toString());
        param.add(ePueblo.getText().toString());
        param.add(eEtnia.getText().toString());
        param.add(sGrupo_sanguineo.getSelectedItem().toString());
        param.add(sOjos.getSelectedItem().toString());

        //System.out.println("nombre: " + nombre);

    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registrado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), PantallaInicio.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.registro_fallido), Toast.LENGTH_SHORT).show();
        }
    }

    private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... urls) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            boolean result = false;
            try {
                result = ComunicacionUsuarios.registrarRefugiado(url, param);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }
}
