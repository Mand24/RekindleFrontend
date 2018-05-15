package com.example.usuario.rekindlefrontend.view.servicios.editar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

public class EditarServicio extends AppBaseActivity {

    HashMap<String, Fragment> tipoServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        tipoServicios = new HashMap<>();

        tipoServicios.put("Lodge", new EditarAlojamiento ());

        tipoServicios.put("Donation", new EditarDonacion ());

        tipoServicios.put("Education", new EditarCursoEducativo ());

        tipoServicios.put("Job", new EditarOfertaEmpleo ());

        menu ("Lodge");

    }

    public void menu(String tipo_servicio){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace (R.id.editar_servicio, tipoServicios.get(tipo_servicio));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

}
