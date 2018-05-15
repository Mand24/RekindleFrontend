package com.example.usuario.rekindlefrontend.view.servicios.crear;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;

import java.util.HashMap;

public class CrearServicio extends AppBaseActivity {

    HashMap<String, Fragment> tiposFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicio);

        tiposFormulario = new HashMap<>();

        tiposFormulario.put("Lodge", new FormularioAlojamiento());

        tiposFormulario.put("Donation", new FormularioDonacion());

        tiposFormulario.put("Education", new FormularioCursoEducativo());

        tiposFormulario.put("Job", new FormularioOfertaEmpleo());

        menu("Lodge");

    }

    public void menu(String tipo_servicio){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario_servicio, tiposFormulario.get(tipo_servicio));
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
