package com.example.usuario.rekindlefrontend.view.services.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.services.show.ShowService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServicesRefugee extends ListServices {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getSupportActionBar().setTitle(R.string.mis_servicios);
        mMapButton.setVisibility(View.GONE);
    }

    @Override
    protected void setAdapterListener() {

        mAdapter = new ServicesAdapter(getApplicationContext(), mServiciosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), ShowService.class);
                        intent.putExtra("Service", mServices.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, final int position) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder
                                (MyServicesRefugee.this);
                        alertDialog.setTitle(R.string.select_option).setItems(
                                new String[]{getString(R.string.unsubscribe)},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                                MyServicesRefugee.this);

                                        builder.setMessage(R.string.unsubscribe_confirmation);
                                        builder.setCancelable(false);
                                        builder.setPositiveButton(R.string.yes,
                                                new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog,
                                                            int which) {
                                                        sendUnsubscribeService(mServices.get
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

    public void sendUnsubscribeService(Service service) {
        mAPIService.unsubscribeService(Consistency.getUser(this).getMail(), service.getId(),
                service.getServiceType()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.unsubscribed_successfully,
                            Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("CODIGO " + response.code());
                    manageResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("on Failure", t.toString());
                manageResult(false, null);
            }
        });
    }

    @Override
    protected void initializeData(Bundle savedInstance) {
        mAPIService.obtenerMisServicios(Consistency.getUser(this).getMail(), Consistency.getUser
                (this).getUserType(), false)
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