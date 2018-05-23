package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;
import com.google.gson.Gson;

import java.util.HashMap;

public class EditarPerfil extends AppBaseActivity {

    HashMap<String, Fragment> tiposPerfil;
    Voluntario mVoluntario;
    Refugiado mRefugiado;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar_perfil);

        getSupportActionBar().setTitle(R.string.editProfile);

        tiposPerfil = new HashMap<>();

        tiposPerfil.put("Refugee", new EditarPerfilRefugiado());
        tiposPerfil.put("Volunteer", new EditarPerfilVoluntario());

        Usuario user = getUser(this);

        String tipo_usuario = user.getTipo();

        System.out.println("editar" + tipo_usuario);

        if(tipo_usuario.equals("Refugee")){
            mRefugiado = (Refugiado) getIntent().getParcelableExtra("Refugiado");
        } else {
            mVoluntario = (Voluntario) getIntent().getParcelableExtra("Voluntario");
            System.out.println("editaraa"+mVoluntario);
        }
        menu(tipo_usuario);
    }

    public void menu(String tipo_usuario){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(tipo_usuario.equals("Refugee")){
            bundle.putParcelable("Refugiado", mRefugiado);
        } else {
            System.out.println("editara"+mVoluntario.toString());
            bundle.putParcelable("Voluntario", mVoluntario);
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
        Usuario usuario = gson.fromJson(json, Usuario.class);
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
