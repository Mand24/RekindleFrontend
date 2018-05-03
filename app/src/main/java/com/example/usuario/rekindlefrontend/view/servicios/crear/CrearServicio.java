package com.example.usuario.rekindlefrontend.view.servicios.crear;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;

public class CrearServicio extends AppCompatActivity {

    Fragment[] tiposFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicio);

        tiposFormulario = new Fragment[4];

        tiposFormulario[0] = new FormularioAlojamiento();

        tiposFormulario[1] = new FormularioDonacion();

        tiposFormulario[2] = new FormularioCursoEducativo();

        tiposFormulario[3] = new FormularioOfertaEmpleo();

        menu(0);

    }

    public void menu(int tipo_servicio){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario_servicio, tiposFormulario[tipo_servicio]);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }
}
