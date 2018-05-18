package com.example.usuario.rekindlefrontend.view.menu.menuPrincipal;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

public class MenuPrincipal extends AppBaseActivity {

    private int backpress = 0;
    HashMap<String, Fragment> tipos_menu_principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        getSupportActionBar().setTitle(R.string.titulo_menu_principal);

        tipos_menu_principal = new HashMap<>();

        tipos_menu_principal.put("Refugee", new MenuPrincipalRefugiado());
        tipos_menu_principal.put("Volunteer", new MenuPrincipalVoluntario());

        Usuario usuario = getUser(this);

        System.out.println(usuario.toString());

        String tipo_usuario = usuario.getTipo();

        System.out.println("tipo: "+tipo_usuario);

        elegir_tipo_menu_principal(tipo_usuario);

    }

    public void elegir_tipo_menu_principal(String tipo_usuario){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tipo_menu_principal, tipos_menu_principal.get(tipo_usuario));
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), getString (R.string.back_exit), Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            moveTaskToBack(true);
        }
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(MenuPrincipal.this, Login.class);
        startActivity(i);
    }
}
