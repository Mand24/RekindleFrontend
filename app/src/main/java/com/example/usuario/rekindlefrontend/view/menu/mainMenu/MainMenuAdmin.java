package com.example.usuario.rekindlefrontend.view.menu.mainMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.moderate.ListReports;

public class MainMenuAdmin extends Fragment {

    private AppCompatButton button_list_reports;

    public MainMenuAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_administrador, container,
                false);

        //establecer las vistas
        setViews(view);

        button_list_reports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListReports.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setViews(View view) {
        button_list_reports = view.findViewById(R.id.listar_reportes_MenuPrincipalAdministrador);
    }
}

