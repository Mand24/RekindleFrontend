package com.example.usuario.rekindlefrontend.view.servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;

import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.entity.Servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListarServicios extends AppCompatActivity implements Filterable {

    private List<Servicio> servicios = new ArrayList<>();
    private List<Servicio> serviciosFiltrados = new ArrayList<>();
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;

    private ImageButton filtrarAlojamiento, filtrarDonacion, filtrarEducacion, filtrarEmpleo;
    private List<Boolean> filters = new ArrayList<>(
            Arrays.asList(true, true, true, true));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_servicios);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initializeData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter = new ServicesAdapter(getApplicationContext(), serviciosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //TODO:Algo al clicar
                        Intent intent = new Intent(getApplicationContext(), MostrarServicio.class);
                        intent.putExtra("Servicio", servicios.get(position));
                        startActivity(intent);
                    }
                });
        recyclerView.setAdapter(mAdapter);

        filtrarAlojamiento = (ImageButton) findViewById(R.id.boton_tipo_alojamiento);

        filtrarAlojamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get(0)) {
                    filters.set(0, false);
                    filtrarAlojamiento.setBackgroundColor(
                            getResources().getColor(R.color.colorIron));
                } else {
                    filters.set(0, true);
                    filtrarAlojamiento.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                filtrar();
            }
        });

        filtrarDonacion = (ImageButton) findViewById(R.id.boton_tipo_donacion);

        filtrarDonacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get(1)) {
                    filters.set(1, false);
                    filtrarDonacion.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.set(1, true);
                    filtrarDonacion.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                filtrar();
            }
        });

        filtrarEducacion = (ImageButton) findViewById(R.id.boton_tipo_curso_educativo);

        filtrarEducacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get(2)) {
                    filters.set(2, false);
                    filtrarEducacion.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.set(2, true);
                    filtrarEducacion.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                filtrar();
            }
        });

        filtrarEmpleo = (ImageButton) findViewById(R.id.boton_tipo_oferta_empleo);

        filtrarEmpleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get(3)) {
                    filters.set(3, false);
                    filtrarEmpleo.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.set(3, true);
                    filtrarEmpleo.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                filtrar();
            }
        });

    }

    private void filtrar() {
        String output = "";
        serviciosFiltrados = new ArrayList<>();
        for (Servicio s : servicios) {
            if (filters.get(s.getId())) {
                serviciosFiltrados.add(s);
            }
        }
        refreshItems();
    }

    private void refreshItems() {

        mAdapter.setServicios(serviciosFiltrados);
        mAdapter.notifyDataSetChanged();
    }

    private void initializeData() {
        //TODO: Call API
        /*try{
            servicios = new AsyncTaskCall().execute().get();

        }catch (Exception e){
            e.printStackTrace();
        }*/
        servicios.add(new Servicio(0, "Alojamiento", "buena describicion", "Calle 123", "27/07/97",
                "623623623", "4.5", R.drawable.lodging));
        servicios.add(
                new Servicio(2, "Educativo", "buena describicion", "Calle 123342432", "27/07/97",
                        "623623623", "4.5", R.drawable.education));
        servicios.add(new Servicio(1, "Donacion", "buena describicion", "dasdsddssdasd", "27/07/97",
                "623623623", "4.5", R.drawable.donation));
        servicios.add(
                new Servicio(3, "Empleo", "buena describicion", "dsadasd", "27/07/97", "623623623",
                        "4.5", R.drawable.job));
        serviciosFiltrados = servicios;
    }

    /*private class AsyncTaskCall extends AsyncTask<Void, Void, ArrayList<Servicio>> {

        protected void onPreExecute() {
            //showProgress(true);
        }
        @Override
        protected ArrayList<Servicio> doInBackground(Void...voids) {

            String url = getResources().getString(R.string.url_server);
            ArrayList<Servicio> result;
            try {
                result = ComunicacionServicios.listarServicios(url);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }*/

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        search(searchView);
        return true;
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

                if (charString.isEmpty()) {

                    serviciosFiltrados = servicios;
                } else {

                    ArrayList<Servicio> filteredList = new ArrayList<>();

                    for (Servicio row : servicios) {

                        if (row.getNombre().toLowerCase().contains(charString) || row
                                .getDireccion().toLowerCase().contains(charString)) {

                            filteredList.add(row);
                        }
                    }

                    serviciosFiltrados = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = serviciosFiltrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                serviciosFiltrados = (ArrayList<Servicio>) filterResults.values;
                refreshItems();
            }
        };
    }
}

