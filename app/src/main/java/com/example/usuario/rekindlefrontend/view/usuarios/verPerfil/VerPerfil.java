package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.google.gson.Gson;

/**
 * Created by Manel Fernandez on 24-Apr-18.
 */

public class VerPerfil extends AppCompatActivity {

    Fragment[] tiposPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_ver_perfil);

        tiposPerfil = new Fragment[2];

        tiposPerfil[0] = new VerPerfilRefugiado();
        tiposPerfil[1] = new VerPerfilVoluntario();

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
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        i.putExtra("tipo", usuario.getTipo());
        startActivity(i);
    }


}
