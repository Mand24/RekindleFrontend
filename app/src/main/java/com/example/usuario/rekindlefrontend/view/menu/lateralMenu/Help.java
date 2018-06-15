package com.example.usuario.rekindlefrontend.view.menu.lateralMenu;

import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;


public class Help extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle(R.string.titulo_help);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(Help.this, Login.class);
        startActivity(i);
    }
}