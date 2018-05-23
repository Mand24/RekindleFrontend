package com.example.usuario.rekindlefrontend.view.menu.menuPrincipal;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.services.list.ListServices;
import com.example.usuario.rekindlefrontend.view.services.list.MisServiciosRefugiado;
import com.example.usuario.rekindlefrontend.view.usuarios.busqueda.BusquedaRefugiado;
import com.example.usuario.rekindlefrontend.view.usuarios.chat.ListChats;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalRefugiado extends Fragment {

    private AppCompatButton button_listar_servicios;
    private AppCompatButton button_mis_servicios;
    private AppCompatButton button_buscar_personas;
    private AppCompatButton button_chat;


    public MenuPrincipalRefugiado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_refugiado, container, false);

        //establecer las vistas
        setVistas(view);

        button_listar_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListServices.class);
                startActivity(i);
            }
        });

        button_mis_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        MisServiciosRefugiado.class);
                startActivity(i);
            }
        });

        button_buscar_personas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), BusquedaRefugiado.class);
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(), getString (R.string
                        .buscar_personas), Toast.LENGTH_SHORT).show();
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

    public void setVistas(View view){
        button_listar_servicios = view.findViewById(R.id.listar_servicios_MenuPrincipalRefugiado);
        button_mis_servicios = view.findViewById(R.id.mis_servicios_MenuPrincipalRefugiado);
        button_buscar_personas = view.findViewById(R.id.buscar_personas_MenuPrincipalRefugiado);
        button_chat = view.findViewById(R.id.chat);
    }

}
