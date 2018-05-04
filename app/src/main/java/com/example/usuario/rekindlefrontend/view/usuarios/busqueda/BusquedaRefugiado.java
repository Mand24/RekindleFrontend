package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;

import java.util.ArrayList;

/**
 * Created by ORION on 04/05/2018.
 */

public class BusquedaRefugiado extends AbstractFormatChecker{


    private ArrayList<String> param = new ArrayList<String>();

    private EditText eNombre;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;
    private EditText eNacimiento;
    private Spinner sSexo;
    private EditText eProcedencia;
    private EditText ePueblo;
    private EditText eEtnia;
    private Spinner sGrupo_sanguineo;
    private Spinner sOjos;

    private APIService mAPIService;
    private Refugiado refugiado;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_busqueda_refugiado, container, false);

        //establecer las vistas
        setVistas(view);



        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id
                .enviar_registro_refugiado);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    checkCampos(view);
//                    obtenerParametros();
                    /*boolean result = new AsyncTaskCall().execute().get();
                    tratarResultadoPeticion(result);
                    //tratarResultadoPeticion(true);*/
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                sendCreateRefugiado();


            }
        });

        SetDate setDate = new SetDate(eNacimiento, container.getContext());

        return view;
    }

    public void setVistas(View view) {

        eNombre = view.findViewById(R.id.nombre_refugiado);
        ePrimer_apellido = view.findViewById(R.id.p_apellido_refugiado);
        eSegundo_apellido = view.findViewById(R.id.s_apellido_refugiado);
        eNacimiento = view.findViewById(R.id.nacimiento_refugiado);
        sSexo = view.findViewById(R.id.sexo_refugiado);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource
                (getActivity().getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSexo.setAdapter(adapter_sexo);
        eProcedencia = view.findViewById(R.id.procedencia_refugiado);
        ePueblo = view.findViewById(R.id.pueblo_refugiado);
        eEtnia = view.findViewById(R.id.etnia_refugiado);

        sGrupo_sanguineo = view.findViewById(R.id.grupo_sanguineo_refugiado);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource
                (getActivity().getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sGrupo_sanguineo.setAdapter(adapter_gs);

        sOjos = view.findViewById(R.id.ojos_refugiado);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource
                (getActivity().getApplicationContext(), R.array.lista_color_ojos, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sOjos.setAdapter(adapter_ojos);

        mAPIService = APIUtils.getAPIService();
    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());
        checkProcedencia(eProcedencia.getText().toString());
        checkPueblo(ePueblo.getText().toString());
        checkEtnia(eEtnia.getText().toString());
    }


}
