package com.example.usuario.rekindlefrontend.view.users.register;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.rekindlefrontend.R;

import java.util.HashMap;

public class RegisterUser extends AppCompatActivity {

    HashMap<String, Fragment> registerTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        registerTypes = new HashMap<>();

        registerTypes.put("Refugee", new RegisterRefugee());

        registerTypes.put("Volunteer", new RegisterVolunteer());

        menu("Refugee");
    }

    public HashMap<String, Fragment> getFragment () { return registerTypes; }

    public void menu(String type) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario, registerTypes.get(type));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
