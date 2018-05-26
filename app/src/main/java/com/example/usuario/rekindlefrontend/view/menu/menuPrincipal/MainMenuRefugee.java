package com.example.usuario.rekindlefrontend.view.menu.menuPrincipal;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.services.list.ListServices;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesRefugee;
import com.example.usuario.rekindlefrontend.view.usuarios.busqueda.SearchRefugee;
import com.example.usuario.rekindlefrontend.view.usuarios.chat.ListChats;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuRefugee extends Fragment {

    private AppCompatButton button_list_services;
    private AppCompatButton button_my_services;
    private AppCompatButton button_search_people;
    private AppCompatButton button_chat;


    public MainMenuRefugee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_refugiado, container, false);

        setViews(view);

        button_list_services.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListServices.class);
                startActivity(i);
            }
        });

        button_my_services.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        MyServicesRefugee.class);
                startActivity(i);
            }
        });

        button_search_people.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        SearchRefugee.class);
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string
                        .buscar_personas), Toast.LENGTH_SHORT).show();
            }
        });

        button_chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListChats.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setViews(View view) {
        button_list_services = view.findViewById(R.id.listar_servicios_MenuPrincipalRefugiado);
        button_my_services = view.findViewById(R.id.mis_servicios_MenuPrincipalRefugiado);
        button_search_people = view.findViewById(R.id.buscar_personas_MenuPrincipalRefugiado);
        button_chat = view.findViewById(R.id.chat);
    }

}
