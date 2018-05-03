package com.example.usuario.rekindlefrontend.view.servicios;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.servicios.crear.CrearServicio;


/**
 * A simple {@link Fragment} subclass.
 */
public class EleccionTipoServicio extends Fragment {

    private final int[] BOTONESMENU = {R.id.boton_tipo_alojamiento, R.id.boton_tipo_donacion, R.id.boton_tipo_curso_educativo, R.id.boton_tipo_oferta_empleo};

    public EleccionTipoServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu_tipo = inflater.inflate(R.layout.fragment_eleccion_tipo_servicio, container, false);

        ImageButton botonmenu;

        for (int i = 0; i < BOTONESMENU.length; i++){
            botonmenu = (ImageButton) menu_tipo.findViewById(BOTONESMENU[i]);
            final int tipo = i;

            botonmenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Activity actividad = getActivity();
                    ((CrearServicio)actividad).menu(tipo);
                }
            });
        }

        return menu_tipo;
    }

}
