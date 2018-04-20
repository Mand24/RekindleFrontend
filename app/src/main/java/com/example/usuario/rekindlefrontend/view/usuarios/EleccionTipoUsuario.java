package com.example.usuario.rekindlefrontend.view.usuarios;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.usuarios.RegistroUsuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class EleccionTipoUsuario extends Fragment {

    private final int[] BOTONESMENU = {R.id.boton_refugiado, R.id.boton_voluntario};

    public EleccionTipoUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu_eleccion = inflater.inflate(R.layout.fragment_eleccion_tipo_usuario, container, false);

        Button botonmenu;

        for (int i = 0; i < BOTONESMENU.length; i++){
            botonmenu = (Button) menu_eleccion.findViewById(BOTONESMENU[i]);
            final int tipo = i;

            botonmenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Activity actividad = getActivity();
                    ((RegistroUsuario)actividad).menu(tipo);
                }
            });
        }
        return menu_eleccion;

    }

}
