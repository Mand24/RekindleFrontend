package com.example.usuario.rekindlefrontend.view.servicios.listar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.servicios.mostrar.MostrarServicio;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisServiciosRefugiado extends ListarServicios {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getSupportActionBar().setTitle(R.string.mis_servicios);
        mMapButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setAdapterListener() {
        mAdapter = new ServicesAdapter(getApplicationContext(), serviciosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), MostrarServicio.class);
                        intent.putExtra("Servicio", servicios.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, final int position) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder
                                (MisServiciosRefugiado.this);
                        alertDialog.setTitle(R.string.select_option).setItems(
                                new String[]{getString(R.string.unsubscribe)},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                                MisServiciosRefugiado.this);

                                        builder.setMessage(R.string.unsubscribe_confirmation);
                                        builder.setCancelable(false);
                                        builder.setPositiveButton(R.string.yes,
                                                new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog,
                                                            int which) {
                                                        sendUnsubscribeService(servicios.get
                                                                (position));
                                                    }
                                                });

                                        builder.setNegativeButton(R.string.no,
                                                new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog,
                                                            int which) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                });
                        alertDialog.show();
                    }
                });
    }

    public void sendUnsubscribeService(Servicio servicio) {
       mAPIService.unsubscribeService(Consistency.getUser(this).getMail(), servicio.getId(),
               servicio.getTipo()).enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               if (response.isSuccessful()) {
                   Toast.makeText(getApplicationContext(), R.string.unsubscribed_successfully,
                           Toast.LENGTH_SHORT).show();
               } else {
                   System.out.println("CODIGO "+response.code());
                   tratarResultadoPeticion(false, null);
               }
           }

           @Override
           public void onFailure(Call<Void> call, Throwable t) {
               Log.e("on Failure", t.toString());
               tratarResultadoPeticion(false, null);
           }
       });
    }

    @Override
    protected void initializeData(){
        mAPIService.obtenerMisServicios(Consistency.getUser(this).getMail(), Consistency.getUser
                (this).getTipo())
                .enqueue(new Callback<ArrayList<Servicio>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Servicio>> call,
                            Response<ArrayList<Servicio>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Servicio> respuesta = response.body();
                            tratarResultadoPeticion(true, respuesta);
                        } else {
                            System.out.println("CODIGO "+response.code());
                            tratarResultadoPeticion(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Servicio>> call, Throwable t) {

                    }
                });
    }
}