package com.example.usuario.rekindlefrontend.view.menu.menuPrincipal;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.services.create.CreateService;
import com.example.usuario.rekindlefrontend.view.services.list.ListServices;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesVolunteer;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.servicios.crear.CrearServicio;
import com.example.usuario.rekindlefrontend.view.servicios.listar.ListarServicios;
import com.example.usuario.rekindlefrontend.view.servicios.listar.MisServiciosVoluntario;
import com.example.usuario.rekindlefrontend.view.usuarios.chat.ListChats;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuVolunteer extends Fragment {

    private AppCompatButton button_list_services;
    private AppCompatButton button_create_services;
    private AppCompatButton button_my_services;
    private AppCompatButton button_chat;

    public MainMenuVolunteer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_voluntario, container,
                false);
        setViews(view);

        button_list_services.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListServices.class);
                startActivity(i);
            }
        });

        button_create_services.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CreateService.class);
                startActivity(i);
            }
        });

        button_my_services.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        MyServicesVolunteer.class);
                startActivity(i);
            }
        });

        button_chat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListChats.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setViews(View view){
        button_list_services = view.findViewById(R.id.listar_servicios_MenuPrincipalVoluntario);
        button_create_services = view.findViewById(R.id.crear_servicio_MenuPrincipalVoluntario);
        button_my_services = view.findViewById(R.id.mis_servicios_MenuPrincipalVoluntario);
        button_chat = view.findViewById(R.id.chat);

    }

}
