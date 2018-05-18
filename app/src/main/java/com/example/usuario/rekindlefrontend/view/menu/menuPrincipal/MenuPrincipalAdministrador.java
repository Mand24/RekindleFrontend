package com.example.usuario.rekindlefrontend.view.menu.menuPrincipal;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.ListReports;

public class MenuPrincipalAdministrador extends Fragment {

    private AppCompatButton button_listar_reportes;

    public MenuPrincipalAdministrador() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_administrador, container, false);

        //establecer las vistas
        setVistas(view);

        button_listar_reportes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListReports.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setVistas(View view){
        button_listar_reportes = view.findViewById(R.id.listar_reportes_MenuPrincipalAdministrador);
    }
}

