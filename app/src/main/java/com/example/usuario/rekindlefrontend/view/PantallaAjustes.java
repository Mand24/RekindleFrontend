package com.example.usuario.rekindlefrontend.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;

public class PantallaAjustes extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ajustes);
//        MenuItem menuItem = (MenuItem) findViewById(R.id.configuracion);
//        menuItem.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

}
