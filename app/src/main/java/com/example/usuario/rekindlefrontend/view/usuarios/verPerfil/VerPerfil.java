package com.example.usuario.rekindlefrontend.view.usuarios.verPerfil;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

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

        User user = getUser(this);

        String tipo_usuario = user.getUserType();

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
        /*Intent i = new Intent(getApplicationContext(), MainMenu.class);
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        User usuario = gson.fromJson(json, User.class);
        i.putExtra("tipo", usuario.getServiceType());
        startActivity(i);*/
        finish();
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
