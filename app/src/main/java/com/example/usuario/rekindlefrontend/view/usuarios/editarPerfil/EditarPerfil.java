package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;

public class EditarPerfil extends AppCompatActivity {

    Fragment[] tiposPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_editar_perfil);

        tiposPerfil = new Fragment[2];

        tiposPerfil[0] = new EditarPerfilRefugiado();
        tiposPerfil[1] = new EditarPerfilVoluntario();

        int tipo_usuario = getIntent().getIntExtra("tipo", 3);

        menu(tipo_usuario);
    }

    public void menu(int tipo){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.perfilUsuario, tiposPerfil[tipo]);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), VerPerfil.class);
        startActivity(i);
    }
}
