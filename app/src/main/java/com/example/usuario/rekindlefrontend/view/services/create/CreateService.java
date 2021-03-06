package com.example.usuario.rekindlefrontend.view.services.create;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.util.HashMap;

public class CreateService extends AppBaseActivity {

    HashMap<String, Fragment> formTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        getSupportActionBar().setTitle(R.string.crear_servicio);

        formTypes = new HashMap<>();

        formTypes.put("Lodge", new LodgeForm());

        formTypes.put("Donation", new DonationForm());

        formTypes.put("Education", new EducationForm());

        formTypes.put("Job", new JobForm());

        menu("Lodge");

    }

    public void menu(String serviceType) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.formulario_servicio, formTypes.get(serviceType));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
