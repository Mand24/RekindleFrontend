package com.example.usuario.rekindlefrontend.view.users.register;


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
        View selectorMenu = inflater.inflate(R.layout.fragment_eleccion_tipo_usuario, container,
                false);

        Button menuButton;

        BUTTONSMENU.put("Refugee", R.id.boton_refugiado);
        BUTTONSMENU.put("Volunteer", R.id.boton_voluntario);


        for (Map.Entry<String, Integer> entry : BUTTONSMENU.entrySet()) {
            final String key = entry.getKey();
            int value = entry.getValue();
            menuButton = (Button) selectorMenu.findViewById(value);

            menuButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    ((RegisterUser) activity).menu(key);
                }
            });
        }
        return selectorMenu;

    }

}
