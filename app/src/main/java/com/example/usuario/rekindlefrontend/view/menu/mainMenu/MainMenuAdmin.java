package com.example.usuario.rekindlefrontend.view.menu.mainMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.helpLinks.CreateHelpLink;
import com.example.usuario.rekindlefrontend.view.helpLinks.ListHelpLinks;
import com.example.usuario.rekindlefrontend.view.helpLinks.ListHelpLinksAdmin;
import com.example.usuario.rekindlefrontend.view.moderate.ListReports;

public class MainMenuAdmin extends Fragment {

    private AppCompatButton button_list_reports;
    private AppCompatButton button_links;
    private AppCompatButton button_create_link;

    public MainMenuAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_admin, container,
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

        button_links.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListHelpLinksAdmin.class);
                startActivity(i);
            }
        });

        button_create_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CreateHelpLink.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setViews(View view) {
        button_list_reports = view.findViewById(R.id.listar_reportes_MenuPrincipalAdministrador);
        button_links = view.findViewById(R.id.links);
        button_create_link = view.findViewById(R.id.createLink);
    }
}

