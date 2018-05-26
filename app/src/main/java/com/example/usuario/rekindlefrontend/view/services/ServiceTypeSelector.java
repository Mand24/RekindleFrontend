package com.example.usuario.rekindlefrontend.view.services;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.services.create.CreateService;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceTypeSelector extends Fragment {

    private final HashMap<String, Integer> BUTTONSMENU = new HashMap<>();

    public ServiceTypeSelector() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menuType = inflater.inflate(R.layout.fragment_eleccion_tipo_servicio, container,
                false);

        ImageButton buttonMenu;

        BUTTONSMENU.put("Lodge", R.id.boton_tipo_alojamiento);
        BUTTONSMENU.put("Donation", R.id.boton_tipo_donacion);
        BUTTONSMENU.put("Education", R.id.boton_tipo_curso_educativo);
        BUTTONSMENU.put("Job", R.id.boton_tipo_oferta_empleo);

        for (Map.Entry<String, Integer> entry : BUTTONSMENU.entrySet()) {
            final String key = entry.getKey();
            int value = entry.getValue();
            buttonMenu = (ImageButton) menuType.findViewById(value);

            buttonMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    ((CreateService) activity).menu(key);
                }
            });
        }

        return menuType;
    }

}
