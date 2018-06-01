package com.example.usuario.rekindlefrontend.view.helpLinks;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.LinksAdapter;
import com.example.usuario.rekindlefrontend.data.entity.link.Link;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHelpLinksAdmin extends ListHelpLinks{

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    protected void setAdapterListener() {
        mAdapter = new LinksAdapter(getApplicationContext(), mFilteredLinks,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        /*Intent intent = new Intent(getApplicationContext(),
                                                    EditLink.class);
                                            intent.putExtra("Link", mFilteredLinks.get
                                                    (position));
                                            startActivity(intent);*/
                        Toast.makeText(getApplicationContext
                                (), "not implemented!", Toast
                                .LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View v, final int position) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder
                                (ListHelpLinksAdmin.this);
                        alertDialog.setTitle(R.string.select_option).setItems(R.array.clic_servicio,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Editar
                                        if (which == 0) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    EditHelpLink.class);
                                            intent.putExtra("Link", mFilteredLinks.get
                                                    (position));
                                            startActivity(intent);
                                        } else if (which == 1) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    ListHelpLinksAdmin.this);

                                            builder.setMessage(R.string
                                                    .delete_link_confirmation);
                                            builder.setCancelable(false);
                                            builder.setPositiveButton(R.string.yes,
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog,
                                                                int which) {
                                                            sendDeleteLink(mFilteredLinks.get(position));
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

    public void sendDeleteLink(Link link){
        mAPIService.deleteLink(link.getIdLink()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), R.string
                                    .link_deleted_successfully,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    manageResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                manageResult(false, null);
            }
        });
    }


}
