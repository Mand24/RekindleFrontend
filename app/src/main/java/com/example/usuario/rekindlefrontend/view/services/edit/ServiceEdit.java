package com.example.usuario.rekindlefrontend.view.servicios.editar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceEdit extends AppBaseActivity {

    HashMap<String, Fragment> tipoServicios;
    private Alojamiento mLodge;
    private Donacion mDonation;
    private CursoEducativo mEducation;
    private OfertaEmpleo mJob;
    private APIService mAPIService;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        getSupportActionBar().setTitle(R.string.title_activity_editar_servicio);

        tipoServicios = new HashMap<>();

        tipoServicios.put("Lodge", new EditarAlojamiento ());

        tipoServicios.put("Donation", new EditarDonacion ());

        tipoServicios.put("Education", new EditarCursoEducativo ());

        tipoServicios.put("Job", new EditarOfertaEmpleo ());

        mAPIService = APIUtils.getAPIService();

        String typeService = getIntent().getStringExtra("typeService");
        Servicio service = (Servicio) getIntent().getSerializableExtra("Service");

        if(typeService.equals("Lodge")){
            mAPIService.getAlojamiento(service.getId()).enqueue(new Callback<Alojamiento>() {
                @Override
                public void onResponse(Call<Alojamiento> call, Response<Alojamiento> response) {
                    if (response.isSuccessful()){
                        mLodge = response.body();
                        bundle.putSerializable("Lodge", mLodge);
                        manageResult(true, "Lodge");
                    }
                    else manageResult(false, "Lodge");
                }

                @Override
                public void onFailure(Call<Alojamiento> call, Throwable t) {
                    manageResult(false, "Lodge");
                }
            });
        }
        else if(typeService.equals("Donation")){
            mAPIService.getDonacion(service.getId()).enqueue(new Callback<Donacion>() {
                @Override
                public void onResponse(Call<Donacion> call, Response<Donacion> response) {
                    if (response.isSuccessful()){
                        mDonation = response.body();
                        bundle.putSerializable("Donation", mDonation);
                        manageResult(true, "Donation");
                    }
                    else manageResult(false, "Donation");
                }

                @Override
                public void onFailure(Call<Donacion> call, Throwable t) {
                    manageResult(false, "Donation");
                }
            });
        }
        else if(typeService.equals("Education")){
            mAPIService.getCurso(service.getId()).enqueue(new Callback<CursoEducativo>() {
                @Override
                public void onResponse(Call<CursoEducativo> call,
                        Response<CursoEducativo> response) {
                    if (response.isSuccessful()){
                        mEducation = response.body();
                        bundle.putSerializable("Education", mEducation);
                        manageResult(true, "Education");
                    }
                    else manageResult(false, "Education");
                }

                @Override
                public void onFailure(Call<CursoEducativo> call, Throwable t) {
                    manageResult(false, "Education");
                }
            });
        }
        else{
            mAPIService.getEmpleo(service.getId()).enqueue(new Callback<OfertaEmpleo>() {
                @Override
                public void onResponse(Call<OfertaEmpleo> call, Response<OfertaEmpleo> response) {
                    if (response.isSuccessful()){
                        mJob = response.body();
                        bundle.putSerializable("Job", mJob);
                        manageResult(true, "Job");
                    }
                    else manageResult(false, "Job");
                }

                @Override
                public void onFailure(Call<OfertaEmpleo> call, Throwable t) {
                    manageResult(false, "Job");
                }
            });
        }
    }

    public void manageResult(boolean result, String type){
        if (result) {
            tipoServicios.put("Lodge", new EditarAlojamiento());
            tipoServicios.get("Lodge").setArguments(bundle);
            tipoServicios.put("Donation", new EditarDonacion());
            tipoServicios.get("Donation").setArguments(bundle);
            tipoServicios.put("Education", new EditarCursoEducativo());
            tipoServicios.get("Education").setArguments(bundle);
            tipoServicios.put("Job", new EditarOfertaEmpleo());
            tipoServicios.get("Job").setArguments(bundle);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace (R.id.editar_servicio, tipoServicios.get(type));
            transaction.commit();
        } else {
            Log.i("ERROR", "ERROR");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

}
