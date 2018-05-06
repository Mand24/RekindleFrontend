package com.example.usuario.rekindlefrontend.view.servicios.editar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;

public class EditarServicio extends AppCompatActivity {

    Fragment[] tipoServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        tipoServicios = new Fragment[4];

        tipoServicios[0] = new EditarAlojamiento ();

        tipoServicios[1] = new EditarDonacion ();

        tipoServicios[2] = new EditarCursoEducativo ();

        tipoServicios[3] = new EditarOfertaEmpleo ();

        menu (3);

    }

    public void menu(int tipo_servicio){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace (R.id.editar_servicio, tipoServicios[tipo_servicio]);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        // implementar
    }

}
