package com.example.usuario.rekindlefrontend.view.usuarios.registro;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.R;

import java.util.HashMap;

public class RegistroUsuario extends AppCompatActivity {

    HashMap<String, Fragment> tiposRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        tiposRegistro = new HashMap<>();

        tiposRegistro.put("Refugee", new RegistroRefugiado());

        tiposRegistro.put("Volunteer", new RegistroVoluntario());

        menu("Refugee");
    }

    public void menu(String tipo){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario, tiposRegistro.get(tipo));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}
