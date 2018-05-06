package com.example.usuario.rekindlefrontend.view.servicios.mostrar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostrarServicio extends AppCompatActivity {

    Fragment[] tipoServicio;
    private APIService mAPIService;
    private Alojamiento mAlojamiento;
    private Donacion mDonacion;
    private CursoEducativo mCursoEducativo;
    private OfertaEmpleo mOfertaEmpleo;
    private FragmentTransaction transaction;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        Servicio servicio = (Servicio) getIntent().getSerializableExtra("Servicio");

        int tipo_servicio = servicio.getTipo();

        tipoServicio = new Fragment[4];

        mAPIService = APIUtils.getAPIService();

        if(tipo_servicio == 0){
            mAPIService.getAlojamiento(servicio.getId()).enqueue(new Callback<Alojamiento>() {
                @Override
                public void onResponse(Call<Alojamiento> call, Response<Alojamiento> response) {
                    if (response.isSuccessful()){
                        mAlojamiento = response.body();
                        bundle.putSerializable("servicioFrag", mAlojamiento);
                        tratarResultadoPeticion(true, 0);
                    }
                    else tratarResultadoPeticion(false, 0);
                }

                @Override
                public void onFailure(Call<Alojamiento> call, Throwable t) {
                    tratarResultadoPeticion(false, 0);
                }
            });
        }
        else if(tipo_servicio == 1){
            mAPIService.getDonacion(servicio.getId()).enqueue(new Callback<Donacion>() {
                @Override
                public void onResponse(Call<Donacion> call, Response<Donacion> response) {
                    if (response.isSuccessful()){
                        mDonacion = response.body();
                        bundle.putSerializable("servicioFrag", mDonacion);
                        tratarResultadoPeticion(true, 1);
                    }
                    else tratarResultadoPeticion(false, 0);
                }

                @Override
                public void onFailure(Call<Donacion> call, Throwable t) {
                    tratarResultadoPeticion(false, 0);
                }
            });
        }
        else if(tipo_servicio == 2){
            mAPIService.getCurso(servicio.getId()).enqueue(new Callback<CursoEducativo>() {
                @Override
                public void onResponse(Call<CursoEducativo> call,
                        Response<CursoEducativo> response) {
                    if (response.isSuccessful()){
                        mCursoEducativo = response.body();
                        bundle.putSerializable("servicioFrag", mCursoEducativo);
                        tratarResultadoPeticion(true, 2);
                    }
                    else tratarResultadoPeticion(false, 0);
                }

                @Override
                public void onFailure(Call<CursoEducativo> call, Throwable t) {
                    tratarResultadoPeticion(false, 0);
                }
            });
        }
        else{
            mAPIService.getEmpleo(servicio.getId()).enqueue(new Callback<OfertaEmpleo>() {
                @Override
                public void onResponse(Call<OfertaEmpleo> call, Response<OfertaEmpleo> response) {
                    if (response.isSuccessful()){
                        mOfertaEmpleo = response.body();
                        bundle.putSerializable("servicioFrag", mOfertaEmpleo);
                        tratarResultadoPeticion(true, 3);
                    }
                    else tratarResultadoPeticion(false, 0);
                }

                @Override
                public void onFailure(Call<OfertaEmpleo> call, Throwable t) {
                    tratarResultadoPeticion(false, 0);
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void tratarResultadoPeticion(boolean result, int tipo) {

        if (result) {
            tipoServicio[0] = new MostrarAlojamiento();
            tipoServicio[0].setArguments(bundle);
            tipoServicio[1] = new MostrarDonacion();
            tipoServicio[1].setArguments(bundle);
            tipoServicio[2] = new MostrarCursoEducativo();
            tipoServicio[2].setArguments(bundle);
            tipoServicio[3] = new MostrarOfertaEmpleo();
            tipoServicio[3].setArguments(bundle);

            FragmentManager manager = getFragmentManager();
            transaction = manager.beginTransaction();
            if(tipo == 0){
                transaction.replace(R.id.servicio, tipoServicio[0]);
            }
            else if(tipo == 1){
                transaction.replace(R.id.servicio, tipoServicio[1]);
            }
            else if(tipo == 2){
                transaction.replace(R.id.servicio, tipoServicio[2]);
            }
            else{
                transaction.replace(R.id.servicio, tipoServicio[3]);
            }
            transaction.commit();

        } else {
            Log.i("ERROR", "ERROR");
        }
    }

}
