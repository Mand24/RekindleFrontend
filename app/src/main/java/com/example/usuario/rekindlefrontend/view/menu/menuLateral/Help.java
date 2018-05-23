package com.example.usuario.rekindlefrontend.view.menu.menuLateral;

import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;


public class Help extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setTitle(R.string.titulo_help);

        //TextView tvTitulo = findViewById(R.id.titulo_help);
        //TextView tvContenido = findViewById(R.id.contenido_help);

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
        Intent i = new Intent(Help.this, Login.class);
        startActivity(i);
    }
}