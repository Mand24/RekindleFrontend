package com.example.usuario.rekindlefrontend;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.view.menu.lateralMenu.About;
import com.example.usuario.rekindlefrontend.view.menu.lateralMenu.Help;
import com.example.usuario.rekindlefrontend.view.menu.lateralMenu.Settings;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;
import com.example.usuario.rekindlefrontend.view.users.show.ShowProfile;

public abstract class AppBaseActivity extends AppCompatActivity {

    protected RelativeLayout view_stub; //This is the framelayout to keep your content view
    protected NavigationView navigationView;
    // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    protected DrawerLayout drawerLayout;
    protected Toolbar mToolbar;
    private TextView userName;
    private TextView userEmail;
    private ImageView userPhoto;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(
                R.layout.activity_app_base);// The base layout that contains your navigation drawer.

        view_stub = (RelativeLayout) findViewById(R.id.view_stub);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        setSupportActionBar(mToolbar);

        View headerView = navigationView.getHeaderView(0);

        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        userName = (TextView) headerView.findViewById(R.id.nombre_header);
        userEmail = (TextView) headerView.findViewById(R.id.email_header);
        userPhoto = (ImageView) headerView.findViewById(R.id.profile_image);

        User user = getUser(this);

        setDataUser(user);


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

                    case R.id.ver_perfil:
                        i = new Intent(getApplicationContext(), ShowProfile.class);
                        startActivity(i);
                        break;
                    case R.id.configuracion:

                        i = new Intent(getApplicationContext(), Settings.class);
                        startActivity(i);
                        break;

                    case R.id.ayuda:

                        i = new Intent(getApplicationContext(), Help.class);
                        startActivity(i);
                        break;
                    case R.id.about:

                        i = new Intent(getApplicationContext(), About.class);
                        startActivity(i);
                        break;
                    case R.id.cerrar_sesion:
                        openDialog();
                        break;
                    default:
                        break;
                }
                return true;

            }
        });
    }

    protected void setDataUser(User user) {
        userName.setText(user.getName() + " " + user.getSurname1());
        userEmail.setText(user.getMail());

        if (user.getPhoto() != null) {
            userPhoto.setImageBitmap(user.getDecodedPhoto());
        } else {
            userPhoto.setImageResource(R.drawable.ic_usuario);
        }
    }

    private void openDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                //TODO pasar todos estos a Strings
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        saveUser(null, getApplicationContext());

                        Toast.makeText(getApplicationContext(), "cerrar sesion!", Toast
                                .LENGTH_SHORT)
                                .show();
                        gotoLaunch();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    protected abstract void gotoLaunch();


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

            case R.id.home:
                Intent i = new Intent(this, MainMenu.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open,
                R.string
                        .drawer_close);
    }

}