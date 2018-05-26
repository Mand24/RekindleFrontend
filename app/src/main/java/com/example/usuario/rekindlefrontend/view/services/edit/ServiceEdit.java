package com.example.usuario.rekindlefrontend.view.services.edit;

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

public class ServiceEdit extends AppBaseActivity {

    HashMap<String, Fragment> tipoServicios;
    private Lodge mLodge;
    private Donation mDonation;
    private Education mEducation;
    private Job mJob;
    private APIService mAPIService;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        getSupportActionBar().setTitle(R.string.title_activity_editar_servicio);

        tipoServicios = new HashMap<>();

        tipoServicios.put("Lodge", new LodgeEdit());

        tipoServicios.put("Donation", new DonationEdit());

        tipoServicios.put("Education", new EducationEdit());

        tipoServicios.put("Job", new JobEdit());

        mAPIService = APIUtils.getAPIService();

        String typeService = getIntent().getStringExtra("typeService");
        Service service = (Service) getIntent().getSerializableExtra("Service");

        if (typeService.equals("Lodge")) {
            mAPIService.getAlojamiento(service.getId()).enqueue(new Callback<Lodge>() {
                @Override
                public void onResponse(Call<Lodge> call, Response<Lodge> response) {
                    if (response.isSuccessful()) {
                        mLodge = response.body();
                        bundle.putSerializable("Lodge", mLodge);
                        manageResult(true, "Lodge");
                    } else {
                        manageResult(false, "Lodge");
                    }
                }

                @Override
                public void onFailure(Call<Lodge> call, Throwable t) {
                    manageResult(false, "Lodge");
                }
            });
        } else if (typeService.equals("Donation")) {
            mAPIService.getDonacion(service.getId()).enqueue(new Callback<Donation>() {
                @Override
                public void onResponse(Call<Donation> call, Response<Donation> response) {
                    if (response.isSuccessful()) {
                        mDonation = response.body();
                        bundle.putSerializable("Donation", mDonation);
                        manageResult(true, "Donation");
                    } else {
                        manageResult(false, "Donation");
                    }
                }

                @Override
                public void onFailure(Call<Donation> call, Throwable t) {
                    manageResult(false, "Donation");
                }
            });
        } else if (typeService.equals("Education")) {
            mAPIService.getCurso(service.getId()).enqueue(new Callback<Education>() {
                @Override
                public void onResponse(Call<Education> call,
                        Response<Education> response) {
                    if (response.isSuccessful()) {
                        mEducation = response.body();
                        bundle.putSerializable("Education", mEducation);
                        manageResult(true, "Education");
                    } else {
                        manageResult(false, "Education");
                    }
                }

                @Override
                public void onFailure(Call<Education> call, Throwable t) {
                    manageResult(false, "Education");
                }
            });
        } else {
            mAPIService.getEmpleo(service.getId()).enqueue(new Callback<Job>() {
                @Override
                public void onResponse(Call<Job> call, Response<Job> response) {
                    if (response.isSuccessful()) {
                        mJob = response.body();
                        bundle.putSerializable("Job", mJob);
                        manageResult(true, "Job");
                    } else {
                        manageResult(false, "Job");
                    }
                }

                @Override
                public void onFailure(Call<Job> call, Throwable t) {
                    manageResult(false, "Job");
                }
            });
        }
    }

    public void manageResult(boolean result, String type) {
        if (result) {
            tipoServicios.put("Lodge", new LodgeEdit());
            tipoServicios.get("Lodge").setArguments(bundle);
            tipoServicios.put("Donation", new DonationEdit());
            tipoServicios.get("Donation").setArguments(bundle);
            tipoServicios.put("Education", new EducationEdit());
            tipoServicios.get("Education").setArguments(bundle);
            tipoServicios.put("Job", new JobEdit());
            tipoServicios.get("Job").setArguments(bundle);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.editar_servicio, tipoServicios.get(type));
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
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

}
