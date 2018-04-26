package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.view.PantallaAjustes;
import com.example.usuario.rekindlefrontend.view.usuarios.VerPerfil;

public abstract class AppBaseActivity extends AppCompatActivity {

//    private ListView listView;
//    private String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

    private RelativeLayout view_stub; //This is the framelayout to keep your content view
    private NavigationView navigationView; // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_app_base);// The base layout that contains your navigation drawer.

//      listView = (ListView) findViewById(R.id.list_view);
        view_stub = (RelativeLayout) findViewById(R.id.view_stub);
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
                        Intent i = new Intent(getApplicationContext(), VerPerfil.class);
                        startActivity(i);
                        break;
                    case R.id.configuracion:
//                        Toast.makeText(getApplicationContext(), "configuracion!", Toast
//                                .LENGTH_SHORT)
//                                .show();
                        Intent ii = new Intent(getApplicationContext(), PantallaAjustes.class);
                        startActivity(ii);
                        break;

                    case R.id.ayuda:
                        Toast.makeText(getApplicationContext(), "help!", Toast
                                .LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.about:
                        Toast.makeText(getApplicationContext(), "about!", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.cerrar_sesion:
                        Toast.makeText(getApplicationContext(), "cerrar sesion!", Toast
                                .LENGTH_SHORT)
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
        // and so on...
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /* Override all setContentView methods to put the content view to the FrameLayout view_stub
     * so that, we can make other activity implementations looks like normal activity subclasses.
     */
    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (view_stub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view_stub.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view_stub != null) {
            view_stub.addView(view, params);
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