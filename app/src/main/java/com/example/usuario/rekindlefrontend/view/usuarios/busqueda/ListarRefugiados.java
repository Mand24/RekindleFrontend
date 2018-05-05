package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

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
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.RefugiadosAdapter;
import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;

import java.util.ArrayList;
import java.util.List;

public class ListarRefugiados extends AppCompatActivity  implements Filterable {

    protected List<Refugiado> refugiados = new ArrayList<>();
    protected List<Refugiado> refugiadosFiltrados = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected RefugiadosAdapter mAdapter;
    protected SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_refugiados);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initializeData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setAdapterListener();

        recyclerView.setAdapter(mAdapter);


    }

    protected void setAdapterListener() {
        mAdapter = new RefugiadosAdapter(getApplicationContext(), refugiadosFiltrados,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        /*Intent intent = new Intent(getApplicationContext(), VerPerfil.class);
                        intent.putExtra("Refugiado", refugiados.get(position));
                        startActivity(intent);*/

                        Toast.makeText(getApplicationContext(), "Mostrar Perfil!!!", Toast
                                .LENGTH_SHORT)
                                .show();
                    }
                });
    }

    protected void refreshItems() {

        mAdapter.setRefugiados(refugiadosFiltrados);
        mAdapter.notifyDataSetChanged();
    }

    private void initializeData() {
        /*refugiados.add(new Refugiado("mail@gmail.com", "1234", "nombre", "apellido1",
                "apellido2", "123456789", "09-02-1995", "Masculino", "España", "Barcelona", "",
                "", "", ""));
        refugiados.add(new Refugiado("mail1@gmail.com", "1234", "nombre1", "apellido1",
                "apellido2", "123456789", "09-02-1995", "Masculino", "España", "Barcelona", "",
                "", "", ""));
        refugiados.add(new Refugiado("mail2@gmail.com", "1234", "nombre2", "apellido1",
                "apellido2", "123456789", "09-02-1995", "Masculino", "España", "Barcelona", "",
                "", "", ""));*/

        refugiados = getIntent().getParcelableArrayListExtra("listRefugiados");

        refugiadosFiltrados = refugiados;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        i.putExtra("tipo", 0);
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

                ArrayList<Refugiado> filteredList = new ArrayList<>();

                for (Refugiado s : refugiados) {
                    if(!charString.isEmpty()) {
                        if (s.getName().toLowerCase().contains(charString) || s
                                .getSurname1().toLowerCase().contains(charString) || s
                                .getSurname2().toLowerCase().contains(charString) || s
                                .getSex().toLowerCase().contains(charString) || s
                                .getBirthDate().toLowerCase().contains(charString)) {

                            filteredList.add(s);
                        }
                    }
                    else{
                        filteredList.add(s);
                    }

                }

                refugiadosFiltrados = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = refugiadosFiltrados;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                refugiadosFiltrados = (ArrayList<Refugiado>) filterResults.values;
                refreshItems();
            }
        };
    }
}
