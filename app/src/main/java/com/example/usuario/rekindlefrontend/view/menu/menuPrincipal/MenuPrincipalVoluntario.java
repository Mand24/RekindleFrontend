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

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalVoluntario extends Fragment {

    private AppCompatButton button_listar_servicios;
    private AppCompatButton button_crear_servicio;
    private AppCompatButton button_mis_servicios;


    public MenuPrincipalVoluntario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal_voluntario, container,
                false);

        //establecer las vistas
        setVistas(view);

        button_listar_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListServices.class);
                startActivity(i);
            }
        });

        button_crear_servicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CreateService.class);
                startActivity(i);
            }
        });

        button_mis_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        MyServicesVolunteer.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void setVistas(View view){
        button_listar_servicios = view.findViewById(R.id.listar_servicios_MenuPrincipalVoluntario);
        button_crear_servicio = view.findViewById(R.id.crear_servicio_MenuPrincipalVoluntario);
        button_mis_servicios = view.findViewById(R.id.mis_servicios_MenuPrincipalVoluntario);
    }

}
