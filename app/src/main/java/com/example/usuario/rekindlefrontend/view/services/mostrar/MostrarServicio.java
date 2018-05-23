package com.example.usuario.rekindlefrontend.view.services.mostrar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.service.Education;
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostrarServicio extends AppBaseActivity {

    HashMap<String, Fragment> serviceType;
    private APIService mAPIService;
    private Lodge mLodge;
    private Donation mDonation;
    private Education mEducation;
    private Job mJob;
    private FragmentTransaction transaction;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        getSupportActionBar().setTitle(R.string.showService);

        Service service = (Service) getIntent().getSerializableExtra("Service");

        String tipo_servicio = service.getServiceType();

        serviceType = new HashMap<>();

        mAPIService = APIUtils.getAPIService();

        if(tipo_servicio.equals("Lodge")){
            mAPIService.getAlojamiento(service.getId()).enqueue(new Callback<Lodge>() {
                @Override
                public void onResponse(Call<Lodge> call, Response<Lodge> response) {
                    if (response.isSuccessful()){
                        mLodge = response.body();
                        bundle.putSerializable("servicioFrag", mLodge);
                        tratarResultadoPeticion(true, "Lodge");
                    }
                    else tratarResultadoPeticion(false, "Lodge");
                }

                @Override
                public void onFailure(Call<Lodge> call, Throwable t) {
                    tratarResultadoPeticion(false, "Lodge");
                }
            });
        }
        else if(tipo_servicio.equals("Donation")){
            mAPIService.getDonacion(service.getId()).enqueue(new Callback<Donation>() {
                @Override
                public void onResponse(Call<Donation> call, Response<Donation> response) {
                    if (response.isSuccessful()){
                        mDonation = response.body();
                        bundle.putSerializable("servicioFrag", mDonation);
                        tratarResultadoPeticion(true, "Donation");
                    }
                    else tratarResultadoPeticion(false, "Donation");
                }

                @Override
                public void onFailure(Call<Donation> call, Throwable t) {
                    tratarResultadoPeticion(false, "Donation");
                }
            });
        }
        else if(tipo_servicio.equals("Course")){
            mAPIService.getCurso(service.getId()).enqueue(new Callback<Education>() {
                @Override
                public void onResponse(Call<Education> call,
                        Response<Education> response) {
                    if (response.isSuccessful()){
                        mEducation = response.body();
                        bundle.putSerializable("servicioFrag", mEducation);
                        tratarResultadoPeticion(true, "Course");
                    }
                    else tratarResultadoPeticion(false, "Course");
                }

                @Override
                public void onFailure(Call<Education> call, Throwable t) {
                    tratarResultadoPeticion(false, "Course");
                }
            });
        }
        else{
            mAPIService.getEmpleo(service.getId()).enqueue(new Callback<Job>() {
                @Override
                public void onResponse(Call<Job> call, Response<Job> response) {
                    if (response.isSuccessful()){
                        mJob = response.body();
                        bundle.putSerializable("servicioFrag", mJob);
                        tratarResultadoPeticion(true, "Job");
                    }
                    else tratarResultadoPeticion(false, "Job");
                }

                @Override
                public void onFailure(Call<Job> call, Throwable t) {
                    tratarResultadoPeticion(false, "Job");
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void tratarResultadoPeticion(boolean result, String tipo) {

        if (result) {
            serviceType.put("Lodge", new MostrarAlojamiento());
            serviceType.get("Lodge").setArguments(bundle);
            serviceType.put("Donation", new MostrarDonacion());
            serviceType.get("Donation").setArguments(bundle);
            serviceType.put("Education", new MostrarCursoEducativo());
            serviceType.get("Education").setArguments(bundle);
            serviceType.put("Job", new MostrarOfertaEmpleo());
            serviceType.get("Job").setArguments(bundle);

            FragmentManager manager = getFragmentManager();
            transaction = manager.beginTransaction();

            transaction.replace(R.id.servicio, serviceType.get(tipo));

            transaction.commit();

        } else {
            Log.i("ERROR", "ERROR");
        }
    }

}
