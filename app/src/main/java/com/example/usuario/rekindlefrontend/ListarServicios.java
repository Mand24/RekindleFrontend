package com.example.usuario.rekindlefrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListarServicios extends AppCompatActivity {

    private List<Servicio> servicios = new ArrayList<>();
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_servicios);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initializeData();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ServicesAdapter(servicios);
        recyclerView.setAdapter(mAdapter);

    }


    private void initializeData(){
        //TODO: Call API
        servicios.add(new Servicio("Alojamiento","buena describicion", "Calle 123", "27/07/97","623623623","4.5", R.drawable.lodging));
        servicios.add(new Servicio("Educativo", "buena describicion", "Calle 123342432","27/07/97","623623623","4.5", R.drawable.education));
        servicios.add(new Servicio("Donacion","buena describicion",  "dasdsddssdasd","27/07/97","623623623","4.5", R.drawable.donation));
        servicios.add(new Servicio("Empleo", "buena describicion", "dsadasd", "27/07/97","623623623","4.5", R.drawable.job));
    }
}

