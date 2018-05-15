package com.example.usuario.rekindlefrontend.view.servicios.mostrar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostrarServicio extends AppBaseActivity {

    HashMap<String, Fragment> tipoServicio;
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

        String tipo_servicio = servicio.getTipo();

        tipoServicio = new HashMap<>();

        mAPIService = APIUtils.getAPIService();

        if(tipo_servicio.equals("Lodge")){
            mAPIService.getAlojamiento(servicio.getId()).enqueue(new Callback<Alojamiento>() {
                @Override
                public void onResponse(Call<Alojamiento> call, Response<Alojamiento> response) {
                    if (response.isSuccessful()){
                        mAlojamiento = response.body();
                        bundle.putSerializable("servicioFrag", mAlojamiento);
                        tratarResultadoPeticion(true, "Lodge");
                    }
                    else tratarResultadoPeticion(false, "Lodge");
                }

                @Override
                public void onFailure(Call<Alojamiento> call, Throwable t) {
                    tratarResultadoPeticion(false, "Lodge");
                }
            });
        }
        else if(tipo_servicio.equals("Donation")){
            mAPIService.getDonacion(servicio.getId()).enqueue(new Callback<Donacion>() {
                @Override
                public void onResponse(Call<Donacion> call, Response<Donacion> response) {
                    if (response.isSuccessful()){
                        mDonacion = response.body();
                        bundle.putSerializable("servicioFrag", mDonacion);
                        tratarResultadoPeticion(true, "Donation");
                    }
                    else tratarResultadoPeticion(false, "Donation");
                }

                @Override
                public void onFailure(Call<Donacion> call, Throwable t) {
                    tratarResultadoPeticion(false, "Donation");
                }
            });
        }
        else if(tipo_servicio.equals("Course")){
            mAPIService.getCurso(servicio.getId()).enqueue(new Callback<CursoEducativo>() {
                @Override
                public void onResponse(Call<CursoEducativo> call,
                        Response<CursoEducativo> response) {
                    if (response.isSuccessful()){
                        mCursoEducativo = response.body();
                        bundle.putSerializable("servicioFrag", mCursoEducativo);
                        tratarResultadoPeticion(true, "Course");
                    }
                    else tratarResultadoPeticion(false, "Course");
                }

                @Override
                public void onFailure(Call<CursoEducativo> call, Throwable t) {
                    tratarResultadoPeticion(false, "Course");
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
                        tratarResultadoPeticion(true, "Job");
                    }
                    else tratarResultadoPeticion(false, "Job");
                }

                @Override
                public void onFailure(Call<OfertaEmpleo> call, Throwable t) {
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
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void tratarResultadoPeticion(boolean result, String tipo) {

        if (result) {
            tipoServicio.put("Lodge", new MostrarAlojamiento());
            tipoServicio.get("Lodge").setArguments(bundle);
            tipoServicio.put("Donation", new MostrarDonacion());
            tipoServicio.get("Donation").setArguments(bundle);
            tipoServicio.put("Education", new MostrarCursoEducativo());
            tipoServicio.get("Education").setArguments(bundle);
            tipoServicio.put("Job", new MostrarOfertaEmpleo());
            tipoServicio.get("Job").setArguments(bundle);

            FragmentManager manager = getFragmentManager();
            transaction = manager.beginTransaction();
            if(tipo.equals("Lodge")){
                transaction.replace(R.id.servicio, tipoServicio.get(tipo));
            }
            else if(tipo.equals("Donation")){
                transaction.replace(R.id.servicio, tipoServicio.get(tipo));
            }
            else if(tipo.equals("Education")){
                transaction.replace(R.id.servicio, tipoServicio.get(tipo));
            }
            else{
                transaction.replace(R.id.servicio, tipoServicio.get(tipo));
            }
            transaction.commit();

        } else {
            Log.i("ERROR", "ERROR");
        }
    }

}
