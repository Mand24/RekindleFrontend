package com.example.usuario.rekindlefrontend;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroRefugiado extends Fragment {


    public RegistroRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_refugiado, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.sexo_refugiado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.grupo_sanguineo_refugiado);

        adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.ojos_refugiado);

        adapter = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return view;
    }

}
