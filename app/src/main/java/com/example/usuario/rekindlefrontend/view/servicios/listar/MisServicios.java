package com.example.usuario.rekindlefrontend.view.servicios.listar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.servicios.mostrar.MostrarServicio;

public class MisServicios extends ListarServicios {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        getSupportActionBar().setTitle(R.string.mis_servicios);
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
                    public void onItemLongClick(View v, int position) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder
                                (MisServicios.this);
                        alertDialog.setTitle("Elije una opcion").setItems(R.array.clic_servicio,
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int which){
                                        //Editar
                                        if(which == 0){
                                            Toast.makeText(getApplicationContext(), " Not implemented ",
                                                    Toast.LENGTH_SHORT).show();
                                        } else if (which == 1){
                                            //TODO:Call API
                                            Toast.makeText(getApplicationContext(), "Not implemented ",
                                                    Toast.LENGTH_SHORT).show();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    MisServicios.this);

                                            builder.setMessage("Are you Sure you want to delete "
                                                    + "the service?");
                                            builder.setCancelable(false);
                                            builder.setPositiveButton("Yes",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog,
                                                                int which) {
                                                            // TODO API delete
                                                            sendEliminarServicio();
                                                            MisServicios.this.finish();
                                                        }
                                                    });

                                            builder.setNegativeButton("No",
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

    public void sendEliminarServicio(){

    }
}

