package com.example.usuario.rekindlefrontend.view.servicios.mostrar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;


public class MostrarServicio extends AppCompatActivity {

    Fragment tipoServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        menu();
    }

    public void menu(){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        tipoServicio = new MostrarAlojamiento();
        transaction.replace(R.id.formulario, tipoServicio);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

}
