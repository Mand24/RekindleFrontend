package com.example.usuario.rekindlefrontend.view.usuarios.registro;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.usuario.rekindlefrontend.R;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTypeSelector extends Fragment {

    private final HashMap<String, Integer> BUTTONSMENU = new HashMap<String, Integer>();

    public UserTypeSelector() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu_eleccion = inflater.inflate(R.layout.fragment_eleccion_tipo_usuario, container, false);

        Button botonmenu;

        BUTTONSMENU.put("Refugee", R.id.boton_refugiado);
        BUTTONSMENU.put("Volunteer", R.id.boton_voluntario);


        for(Map.Entry<String, Integer> entry : BUTTONSMENU.entrySet()) {
            final String key = entry.getKey();
            int value = entry.getValue();
            botonmenu = (Button) menu_eleccion.findViewById(value);

            botonmenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Activity actividad = getActivity();
                    ((RegistroUsuario)actividad).menu(key);
                }
            });
        }
        return menu_eleccion;

    }

}
