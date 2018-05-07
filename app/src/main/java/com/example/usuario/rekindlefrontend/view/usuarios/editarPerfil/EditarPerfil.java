package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

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

public class EditarPerfil extends AppBaseActivity {

    Fragment[] tiposPerfil;
    Voluntario mVoluntario;
    Refugiado mRefugiado;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_editar_perfil);

        tiposPerfil = new Fragment[2];

        tiposPerfil[0] = new EditarPerfilRefugiado();
        tiposPerfil[1] = new EditarPerfilVoluntario();

        int tipo_usuario = getIntent().getIntExtra("tipo", 3);
        mVoluntario = (Voluntario) getIntent().getSerializableExtra("Voluntario");
        mRefugiado = (Refugiado) getIntent().getSerializableExtra("Refugiado");

        menu(tipo_usuario);
    }

    public void menu(int tipo){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        bundle.putParcelable("Voluntario", mVoluntario);
        bundle.putParcelable("Refugiado", mRefugiado);
        transaction.replace(R.id.perfilUsuario, tiposPerfil[tipo]);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), VerPerfil.class);
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        i.putExtra("tipo", usuario.getTipo());
        startActivity(i);
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
