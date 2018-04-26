package com.example.usuario.rekindlefrontend.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;

import java.util.Locale;

public class PantallaAjustes extends AppBaseActivity {

    private Spinner sIdiomas;
    private AppCompatButton bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ajustes);

        //establecer las vistas
        setVistas();

        setIdiomaActualSpinner();
        bGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Locale localizacion;
                Configuration config;
                switch (sIdiomas.getSelectedItemPosition()){

                    case 0:
                        localizacion = new Locale("en", "EN");
                        Locale.setDefault(localizacion);
                        config = new Configuration();
                        config.locale = localizacion;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        break;
                    case 1:
                        localizacion = new Locale("ca", "ES");

                        Locale.setDefault(localizacion);
                        config = new Configuration();
                        config.locale = localizacion;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        break;
                    case 2:
                        localizacion = new Locale("es", "ES");

                        Locale.setDefault(localizacion);
                        config = new Configuration();
                        config.locale = localizacion;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                }

                Intent refrescar = new Intent(getApplicationContext(), PantallaAjustes.class);
                startActivity(refrescar);
                finish();

            }
        });



//        MenuItem menuItem = (MenuItem) findViewById(R.id.configuracion);
//        menuItem.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

    public void setVistas(){
        sIdiomas = findViewById(R.id.idiomas_ajustes);
        ArrayAdapter<CharSequence> adapter_idiomas = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_idiomas, R.layout.spinner_item);
        adapter_idiomas.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sIdiomas.setAdapter(adapter_idiomas);

        bGuardar = findViewById(R.id.guardar_ajustes);
    }

    public void setIdiomaActualSpinner(){
        String codLenguaje= Locale.getDefault().getLanguage();
        System.out.println("idioma: " + codLenguaje);

        switch (codLenguaje){
            case "en":
                sIdiomas.setSelection(0);
                break;
            case "ca":
                sIdiomas.setSelection(1);
                break;
            case "es":
                sIdiomas.setSelection(2);
        }
    }

}
