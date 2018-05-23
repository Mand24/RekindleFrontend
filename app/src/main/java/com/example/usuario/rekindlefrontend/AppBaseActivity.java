package com.example.usuario.rekindlefrontend;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.view.menu.menuLateral.About;
import com.example.usuario.rekindlefrontend.view.menu.menuLateral.Ajustes;
import com.example.usuario.rekindlefrontend.view.menu.menuLateral.Help;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;

public abstract class AppBaseActivity extends AppCompatActivity {

//    private ListView listView;
//    private String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

    protected RelativeLayout view_stub; //This is the framelayout to keep your content view
    protected NavigationView navigationView;
            // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    protected DrawerLayout drawerLayout;
    protected Toolbar mToolbar;
    private TextView nombreUsuario;
    private TextView emailUsuario;
    private ImageView photoUser;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(
                R.layout.activity_app_base);// The base layout that contains your navigation drawer.
//      listView = (ListView) findViewById(R.id.list_view);
        view_stub = (RelativeLayout) findViewById(R.id.view_stub);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        setSupportActionBar(mToolbar);

        View headerView = navigationView.getHeaderView(0);

        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        nombreUsuario = (TextView) headerView.findViewById(R.id.nombre_header);
        emailUsuario = (TextView) headerView.findViewById(R.id.email_header);
        photoUser = (ImageView) headerView.findViewById(R.id.profile_image);

       /* SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
       (getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);*/

        Usuario usuario = getUser(this);

        nombreUsuario.setText(usuario.getName() + " " + usuario.getSurname1());
        emailUsuario.setText(usuario.getMail());

        if(usuario.getPhoto() != null) {
            photoUser.setImageBitmap(usuario.getDecodedPhoto());
        }else{
            photoUser.setImageResource(R.drawable.ic_usuario);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                Intent i;

                switch (menuItem.getItemId()) {
                    /*Se define la lógica de casos que puedan producirse al seleccionar cualquier
                     elemento del menú.*/
                    case R.id.ver_perfil:
                        i = new Intent(getApplicationContext(), VerPerfil.class);
                        startActivity(i);
                        break;
                    case R.id.configuracion:
//                        Toast.makeText(getApplicationContext(), "configuracion!", Toast
//                                .LENGTH_SHORT)
//                                .show();
                        i = new Intent(getApplicationContext(), Ajustes.class);
                        startActivity(i);
                        break;

                    case R.id.ayuda:
//                        Toast.makeText(getApplicationContext(), "help!", Toast
//                                .LENGTH_SHORT)
//                                .show();
                        i = new Intent(getApplicationContext(), Help.class);
                        startActivity(i);
                        break;
                    case R.id.about:
//                      Toast.makeText(getApplicationContext(), "about!", Toast.LENGTH_SHORT)
//                                .show();
                        i = new Intent(getApplicationContext(), About.class);
                        startActivity(i);
                        break;
                    case R.id.cerrar_sesion:
                        openDialog();
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

    private void openDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                        (getApplicationContext());
                        SharedPreferences.Editor miEditor = datos.edit();
                        miEditor.putString("usuario","");
                        miEditor.apply();*/

                        saveUser(null, getApplicationContext());

                        Toast.makeText(getApplicationContext(), "cerrar sesion!", Toast
                                .LENGTH_SHORT)
                                .show();
                        gotoInicio();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    protected abstract void gotoInicio();


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.show_lateral_menu:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
            case R.id.home:
                Intent i = new Intent(this, MenuPrincipal.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, mToolbar,R.string.drawer_open,R.string
                .drawer_close);
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