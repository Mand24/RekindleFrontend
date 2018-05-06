package com.example.usuario.rekindlefrontend.view.servicios.mostrar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;


public class MostrarServicio extends AppCompatActivity {

    Fragment[] tipoServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        tipoServicio = new Fragment[2];

        tipoServicio[0] = new MostrarAlojamiento();
        tipoServicio[1] = new MostrarDonacion();
        tipoServicio[2] = new MostrarCursoEducativo();
        tipoServicio[3] = new MostrarOfertaEmpleo();

        int tipo_servicio = getIntent().getIntExtra("tipo", 0);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(tipo_servicio == 0){
            transaction.replace(R.id.servicio, tipoServicio[0]);
        }
        else if(tipo_servicio == 1){
            transaction.replace(R.id.servicio, tipoServicio[1]);
        }
        else if(tipo_servicio == 2){
            transaction.replace(R.id.servicio, tipoServicio[2]);
        }
        else{
            transaction.replace(R.id.servicio, tipoServicio[3]);
        }
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

}
