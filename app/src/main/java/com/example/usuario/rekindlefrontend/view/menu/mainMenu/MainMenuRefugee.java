package com.example.usuario.rekindlefrontend.view.menu.mainMenu;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.chat.ListChats;
import com.example.usuario.rekindlefrontend.view.helpLinks.ListHelpLinks;
import com.example.usuario.rekindlefrontend.view.services.list.ListServices;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesRefugee;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesRefugeeEnded;
import com.example.usuario.rekindlefrontend.view.users.search.SearchRefugee;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuRefugee extends Fragment {

    private AppCompatButton button_list_services;
    private AppCompatButton button_my_services;
    private AppCompatButton button_valorations;
    private AppCompatButton button_search_people;
    private AppCompatButton button_chat;
    private AppCompatButton button_links;


    public MainMenuRefugee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_refugee, container, false);

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

        button_valorations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        MyServicesRefugeeEnded.class);
                startActivity(i);
            }
        });

        button_search_people.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        SearchRefugee.class);
                startActivity(i);
            }
        });

        button_chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListChats.class);
                startActivity(i);
            }
        });

        button_links.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListHelpLinks.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setViews(View view) {
        button_list_services = view.findViewById(R.id.listar_servicios_MenuPrincipalRefugiado);
        button_my_services = view.findViewById(R.id.mis_servicios_MenuPrincipalRefugiado);
        button_valorations = view.findViewById(R.id.valorations);
        button_search_people = view.findViewById(R.id.buscar_personas_MenuPrincipalRefugiado);
        button_chat = view.findViewById(R.id.chat);
        button_links = view.findViewById(R.id.links);
    }

}
