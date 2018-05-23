package com.example.usuario.rekindlefrontend.view.services.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.services.show.ShowService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServicesVolunteer extends ListServices {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        getSupportActionBar().setTitle(R.string.mis_servicios);
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
                                (MyServicesVolunteer.this);
                        alertDialog.setTitle(R.string.select_option).setItems(R.array.clic_servicio,
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int which){
                                        //Editar
                                        if(which == 0){
                                            Toast.makeText(getApplicationContext(), " Not implemented ",
                                                    Toast.LENGTH_SHORT).show();
                                        } else if (which == 1){
                                            //TODO:Call API
                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    MyServicesVolunteer.this);

                                            builder.setMessage(R.string
                                                    .delete_service_confirmation);
                                            builder.setCancelable(false);
                                            builder.setPositiveButton(R.string.yes,
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog,
                                                                int which) {
                                                            // TODO API delete
                                                            sendDeleteService(mServices.get
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
                                    }
                                });
                        alertDialog.show();
                    }
                });
    }

    public void sendDeleteService(Service service){
        mAPIService.eliminarServicio(service.getId(), service.getServiceType()).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string
                                            .service_deleted_successfully,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("CODIGO "+response.code());
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
    protected void initializeData(){
        mAPIService.obtenerMisServicios(Consistency.getUser(this).getMail(), Consistency.getUser
                (this).getUserType())
                .enqueue(new Callback<ArrayList<Service>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Service>> call,
                            Response<ArrayList<Service>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Service> respuesta = response.body();
                            manageResult(true, respuesta);
                        } else {
                            System.out.println("CODIGO "+response.code());
                            manageResult(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Service>> call, Throwable t) {

                    }
                });
    }
}

