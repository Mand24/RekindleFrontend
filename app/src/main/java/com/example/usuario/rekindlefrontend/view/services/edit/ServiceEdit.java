package com.example.usuario.rekindlefrontend.view.services.edit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

public class ServiceEdit extends AppBaseActivity {

    HashMap<String, Fragment> ServicesTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        getSupportActionBar().setTitle(R.string.title_activity_editar_servicio);

        ServicesTypes = new HashMap<>();

        ServicesTypes.put("Lodge", new LodgeEdit());

        ServicesTypes.put("Donation", new DonationEdit());

        ServicesTypes.put("Education", new EducationEdit());

        ServicesTypes.put("Job", new JobEdit());

        menu ("Lodge");


    }

    public void menu(String ServiceType){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace (R.id.editar_servicio, ServicesTypes.get(ServiceType));
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
