package com.example.usuario.rekindlefrontend.view.menu.menuLateral;

import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

/**
 * Created by Manel Fernandez on 27-Apr-18.
 */

public class About extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle(R.string.about);
    }

    @Override
    public void onBackPressed() {

        /*Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
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
        Intent i = new Intent(About.this, Login.class);
        startActivity(i);
    }
}
