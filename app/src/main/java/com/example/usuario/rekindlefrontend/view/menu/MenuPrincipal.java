package com.example.usuario.rekindlefrontend.view.menu;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.servicios.CrearServicio;
import com.example.usuario.rekindlefrontend.view.servicios.ListarServicios;
import com.example.usuario.rekindlefrontend.view.usuarios.VerPerfil;

public class MenuPrincipal extends AppCompatActivity {

    private int backpress = 0;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
//    private ListView listView;
//    private String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

//        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId())
                {
                 /*Se define la lógica de casos que puedan producirse al seleccionar cualquier elemento del menú.*/
                    case R.id.ver_perfil:
                        Toast.makeText(getApplicationContext(), "ver perfil!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.configuracion:
                        Toast.makeText(getApplicationContext(), "configuracion!", Toast
                                .LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.about:
                        Toast.makeText(getApplicationContext(), "about!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                return true;

            }
         });

//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                android.R.id.text1,opciones));
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                    long arg3) {
//                Toast.makeText(getApplicationContext(), "Item: " + opciones[arg2],
//                        Toast.LENGTH_SHORT).show();
//                drawerLayout.closeDrawers();
//            }
//        });

        // Mostramos el botón en la barra de la aplicación
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Button button_listar_servicios = (Button) findViewById(R.id.listar_servicios_MenuPrincipal);
        button_listar_servicios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListarServicios.class);
                startActivity(i);
            }
        });

        Button button_crear_servicio = (Button) findViewById(R.id.crear_servicio_MenuPrincipal);
        button_crear_servicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CrearServicio.class);
                startActivity(i);
            }
        });

        Button button_ver_perfil = (Button) findViewById(R.id.ver_perfil_MenuPrincipal);
        button_ver_perfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VerPerfil.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
            startActivity(i);
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                if (drawerLayout.isDrawerOpen(listView)) {
//                    drawerLayout.closeDrawers();
//                } else {
//                    drawerLayout.openDrawer(listView);
//                }
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
