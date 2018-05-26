package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

public class EditarPerfil extends AppBaseActivity {

    HashMap<String, Fragment> tiposPerfil;
    Volunteer mVolunteer;
    Refugee mRefugee;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_perfil);

        getSupportActionBar().setTitle(R.string.editProfile);

        tiposPerfil = new HashMap<>();

        tiposPerfil.put("Refugee", new EditarPerfilRefugiado());
        tiposPerfil.put("Volunteer", new EditarPerfilVoluntario());

        User user = getUser(this);

        String tipo_usuario = user.getUserType();

        System.out.println("editar" + tipo_usuario);

        if(tipo_usuario.equals("Refugee")){
            mRefugee = (Refugee) getIntent().getParcelableExtra("Refugee");
        } else {
            mVolunteer = (Volunteer) getIntent().getParcelableExtra("Volunteer");
            System.out.println("editaraa"+ mVolunteer);
        }
        menu(tipo_usuario);
    }

    public void menu(String tipo_usuario){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(tipo_usuario.equals("Refugee")){
            bundle.putParcelable("Refugee", mRefugee);
        } else {
            System.out.println("editara"+ mVolunteer.toString());
            bundle.putParcelable("Volunteer", mVolunteer);
        }

        transaction.replace(R.id.perfilUsuario, tiposPerfil.get(tipo_usuario));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        /*Intent i = new Intent(getApplicationContext(), VerPerfil.class);
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        User usuario = gson.fromJson(json, User.class);
        i.putExtra("tipo", usuario.getServiceType());
        startActivity(i);*/
        //TODO testear esto
        finish();
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
