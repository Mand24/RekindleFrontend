package com.example.usuario.rekindlefrontend.view.services.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.services.EvaluateService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServicesRefugeeEnded extends ListServices {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getSupportActionBar().setTitle(R.string.mis_servicios);
        map_and_filter.setVisibility(View.GONE);
    }

    @Override
    protected void setAdapterListener() {

        mAdapter = new ServicesAdapter(getApplicationContext(), mServiciosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), EvaluateService.class);
                        intent.putExtra("Service", mServiciosFiltrados.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }

    @Override
    protected void initializeData(Bundle savedInstance) {
        mAPIService.obtenerMisServicios(Consistency.getUser(this).getMail(), Consistency.getUser
                (this).getUserType(), true)
                .enqueue(new Callback<ArrayList<Service>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Service>> call,
                            Response<ArrayList<Service>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Service> respuesta = response.body();
                            manageResult(true, respuesta);
                        } else {
                            System.out.println("CODIGO " + response.code());
                            manageResult(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Service>> call, Throwable t) {

                    }
                });
    }
}
