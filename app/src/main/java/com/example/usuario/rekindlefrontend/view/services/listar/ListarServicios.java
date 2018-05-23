package com.example.usuario.rekindlefrontend.view.services.listar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.view.services.mostrar.MostrarServicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarServicios extends AppBaseActivity implements Filterable {

    protected List<Service> mServices = new ArrayList<>();
    protected List<Service> mServiciosFiltrados = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected ServicesAdapter mAdapter;
    protected SearchView searchView;

    protected APIService mAPIService;

    protected ImageButton filtrarAlojamiento, filtrarDonacion, filtrarEducacion, filtrarEmpleo;
    protected HashMap<String, Boolean> filters = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_servicios);

        getSupportActionBar().setTitle(R.string.listar_servicios);

        filters.put("Lodge", true);
        filters.put("Donation", true);
        filters.put("Education", true);
        filters.put("Job", true);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initializeData();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setAdapterListener();

        recyclerView.setAdapter(mAdapter);

        filtrarAlojamiento = (ImageButton) findViewById(R.id.boton_tipo_alojamiento);

        filtrarAlojamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Lodge")) {
                    filters.put("Lodge", false);
                    filtrarAlojamiento.setBackgroundColor(
                            getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Lodge", true);
                    filtrarAlojamiento.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        filtrarDonacion = (ImageButton) findViewById(R.id.boton_tipo_donacion);

        filtrarDonacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Donation")) {
                    filters.put("Donation", false);
                    filtrarDonacion.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Donation", true);
                    filtrarDonacion.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        filtrarEducacion = (ImageButton) findViewById(R.id.boton_tipo_curso_educativo);

        filtrarEducacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Education")) {
                    filters.put("Education", false);
                    filtrarEducacion.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Education", true);
                    filtrarEducacion.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        filtrarEmpleo = (ImageButton) findViewById(R.id.boton_tipo_oferta_empleo);

        filtrarEmpleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Job")) {
                    filters.put("Job", false);
                    filtrarEmpleo.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Job", true);
                    filtrarEmpleo.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

    }

    protected void setAdapterListener() {
        mAdapter = new ServicesAdapter(getApplicationContext(), mServiciosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //TODO:Algo al clicar
                        Intent intent = new Intent(getApplicationContext(), MostrarServicio.class);
                        intent.putExtra("Service", mServiciosFiltrados.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {

                    }
                });
    }

    /*private void filtrar() {
        String output = "";
        mServiciosFiltrados = new ArrayList<>();
        for (Service s : mServices) {
            if (filters.get(s.getId())) {
                mServiciosFiltrados.add(s);
            }
        }
        refreshItems();
    }*/

    protected void refreshItems() {

        mAdapter.setServices(mServiciosFiltrados);
        mAdapter.notifyDataSetChanged();
    }

    protected void initializeData() {

        mAPIService.obtenerServicios().enqueue(new Callback<ArrayList<Service>>
                () {
            @Override
            public void onResponse(Call<ArrayList<Service>> call,
                    Response<ArrayList<Service>>
                            response) {
                if (response.isSuccessful()) {
                    ArrayList<Service> respuesta = response.body();
                    //mServices = response.body();
                    tratarResultadoPeticion(true, respuesta);

                } else {
                    System.out.println("CODIGO "+response.code());
                    tratarResultadoPeticion(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Service>> call, Throwable t) {
                Log.e("on Failure", t.toString());
                tratarResultadoPeticion(false, null);
                Log.i(t.getClass().toString(), "========================");
                if (t instanceof IOException) {
                    Log.i( "NETWORK ERROR", "=======================================");
                    // logging probably not necessary
                }
                else {
                    Log.i( "CONVERSION ERROR", "=======================================");
                }
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        search(searchView);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.show_lateral_menu:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
            case R.id.home:
                Intent i = new Intent(this, MenuPrincipal.class);
                startActivity(i);
                return true;
            case R.id.search:
                searchView = (SearchView) item.getActionView();
                search(searchView);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                ArrayList<Service> filteredList = new ArrayList<>();

                for (Service s : mServices) {
                    if (filters.get(s.getServiceType())) {
                        if (!charString.isEmpty()) {
                            if (s.getName().toLowerCase().contains(charString) || s
                                    .getAdress().toLowerCase().contains(charString)) {

                                filteredList.add(s);
                            }
                        } else {
                            filteredList.add(s);
                        }
                    }
                }

                mServiciosFiltrados = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = mServiciosFiltrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mServiciosFiltrados = (ArrayList<Service>) filterResults.values;
                refreshItems();
            }
        };
    }

    public void tratarResultadoPeticion(boolean result, List<Service> respuesta) {

        if (result) {
            mServices = respuesta;
            mServiciosFiltrados = mServices;
            refreshItems();

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }
}

