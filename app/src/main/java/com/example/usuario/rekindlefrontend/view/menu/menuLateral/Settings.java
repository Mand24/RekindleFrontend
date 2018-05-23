package com.example.usuario.rekindlefrontend.view.menu.menuLateral;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.Locale;

public class Settings extends AppBaseActivity {

    private Spinner sLanguages;
    private AppCompatButton bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ajustes);

        getSupportActionBar().setTitle(R.string.ajustes);

        setViews();

        setCurrentLangSpinner();
        bSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Locale localization;
                Configuration config;
                switch (sLanguages.getSelectedItemPosition()) {

                    case 0:
                        localization = new Locale("en", "EN");
                        Locale.setDefault(localization);
                        config = new Configuration();
                        config.locale = localization;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                        break;
                    case 1:
                        localization = new Locale("ca", "ES");

                        Locale.setDefault(localization);
                        config = new Configuration();
                        config.locale = localization;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                        break;
                    case 2:
                        localization = new Locale("es", "ES");

                        Locale.setDefault(localization);
                        config = new Configuration();
                        config.locale = localization;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());
                        break;
                    default:
                        break;

                }

                Intent refresh = new Intent(getApplicationContext(), Settings.class);
                startActivity(refresh);
                finish();

            }
        });

        navigationView.setCheckedItem(R.id.configuracion);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void setViews() {
        sLanguages = findViewById(R.id.idiomas_ajustes);
        ArrayAdapter<CharSequence> adapter_idiomas = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_idiomas, R.layout.spinner_item);
        adapter_idiomas.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sLanguages.setAdapter(adapter_idiomas);

        bSave = findViewById(R.id.guardar_ajustes);
    }

    public void setCurrentLangSpinner() {
        String codLanguage = Locale.getDefault().getLanguage();
        System.out.println("idioma: " + codLanguage);

        switch (codLanguage) {
            case "en":
                sLanguages.setSelection(0);
                break;
            case "ca":
                sLanguages.setSelection(1);
                break;
            case "es":
                sLanguages.setSelection(2);
                break;
            default:
                sLanguages.setSelection(0);
                break;
        }
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(Settings.this, Login.class);
        startActivity(i);
    }
}
