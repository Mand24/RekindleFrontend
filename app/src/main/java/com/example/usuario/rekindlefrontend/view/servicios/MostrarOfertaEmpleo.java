package com.example.usuario.rekindlefrontend.view.servicios;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.rekindlefrontend.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarOfertaEmpleo extends Fragment {


    public MostrarOfertaEmpleo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mostrar_oferta_empleo, container, false);
    }

}
