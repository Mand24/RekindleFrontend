package com.example.usuario.rekindlefrontend.view.servicios;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.servicios.crear.CreateService;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EleccionTipoServicio extends Fragment {

    private final HashMap<String, Integer> BOTONESMENU = new HashMap<>();

    public EleccionTipoServicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu_tipo = inflater.inflate(R.layout.fragment_eleccion_tipo_servicio, container, false);

        ImageButton botonmenu;

        BOTONESMENU.put("Lodge", R.id.boton_tipo_alojamiento);
        BOTONESMENU.put("Donation", R.id.boton_tipo_donacion);
        BOTONESMENU.put("Education", R.id.boton_tipo_curso_educativo);
        BOTONESMENU.put("Job", R.id.boton_tipo_oferta_empleo);

        for(Map.Entry<String, Integer> entry : BOTONESMENU.entrySet()) {
            final String key = entry.getKey();
            int value = entry.getValue();
            botonmenu = (ImageButton) menu_tipo.findViewById(value);

            botonmenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Activity actividad = getActivity();
                    ((CreateService)actividad).menu(key);
                }
            });
        }

        return menu_tipo;
    }

}
