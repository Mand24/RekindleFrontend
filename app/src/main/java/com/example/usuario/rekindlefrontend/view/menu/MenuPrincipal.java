package com.example.usuario.rekindlefrontend.view.menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.servicios.CrearServicio;
import com.example.usuario.rekindlefrontend.view.servicios.ListarServicios;
import com.example.usuario.rekindlefrontend.view.usuarios.VerPerfil;

public class MenuPrincipal extends AppBaseActivity {

    private int backpress = 0;
    Fragment[] tipos_menu_principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tipos_menu_principal = new Fragment[2];

        tipos_menu_principal[0] = new MenuPrincipalVoluntario();
        tipos_menu_principal[1] = new MenuPrincipalRefugiado();

        elegir_tipo_menu_principal(1);

    }

    public void elegir_tipo_menu_principal(int tipo_usuario){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tipo_menu_principal, tipos_menu_principal[tipo_usuario]);
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            moveTaskToBack(true);
        }
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(MenuPrincipal.this, PantallaInicio.class);
        startActivity(i);
    }
}
