package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Manel Fernandez on 24-Apr-18.
 */

public class VerPerfil extends AppBaseActivity {

    HashMap<String, Fragment> tiposPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver_perfil);

        getSupportActionBar().setTitle(R.string.ver_perfil);

        tiposPerfil = new HashMap<>();

        tiposPerfil.put("Refugee", new VerPerfilRefugiado());
        tiposPerfil.put("Volunteer", new VerPerfilVoluntario());

        Usuario user = getUser(this);

        String tipo_usuario = user.getTipo();

        menu(tipo_usuario);
    }

    public void menu(String tipo_usuario){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.perfilUsuario, tiposPerfil.get(tipo_usuario));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        /*Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        i.putExtra("tipo", usuario.getTipo());
        startActivity(i);*/
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
