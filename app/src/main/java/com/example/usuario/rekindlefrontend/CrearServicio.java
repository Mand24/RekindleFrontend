package com.example.usuario.rekindlefrontend;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
