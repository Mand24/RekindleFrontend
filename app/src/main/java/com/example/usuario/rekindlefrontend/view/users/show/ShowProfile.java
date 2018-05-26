package com.example.usuario.rekindlefrontend.view.users.show;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;


public class ShowProfile extends AppBaseActivity {

    HashMap<String, Fragment> profileTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver_perfil);

        getSupportActionBar().setTitle(R.string.ver_perfil);

        profileTypes = new HashMap<>();

        profileTypes.put("Refugee", new ShowProfileRefugee());
        profileTypes.put("Volunteer", new ShowProfileVolunteer());

        User user = getUser(this);

        String userType = user.getUserType();

        menu(userType);
    }

    public void menu(String userType) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.perfilUsuario, profileTypes.get(userType));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
