package com.example.usuario.rekindlefrontend;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistroUsuario extends AppCompatActivity {

    Fragment [] tiposRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        tiposRegistro = new Fragment[2];

        tiposRegistro[0] = new RegistroRefugiado();

        tiposRegistro[1] = new RegistroVoluntario();

        menu(0);
    }

    public void menu(int tipo){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario, tiposRegistro[tipo]);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
        startActivity(i);
    }
}
