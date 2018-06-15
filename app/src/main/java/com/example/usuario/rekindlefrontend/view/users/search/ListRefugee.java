package com.example.usuario.rekindlefrontend.view.users.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.RefugeeAdapter;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class ListRefugee extends AppBaseActivity implements Filterable {

    protected List<Refugee> mRefugees = new ArrayList<>();
    protected List<Refugee> mRefugeesFiltered = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected RefugeeAdapter mAdapter;
    protected SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_refugees);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        getSupportActionBar().setTitle(R.string.listRefugee);

        initializeData();

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setAdapterListener();

        recyclerView.setAdapter(mAdapter);

    }

    protected void setAdapterListener() {
        mAdapter = new RefugeeAdapter(getApplicationContext(), mRefugeesFiltered,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(),
                                ShowRefugee.class);
                        intent.putExtra("Refugee", mRefugeesFiltered.get(position));
                        System.out.println(mRefugeesFiltered.get(position).toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }

    protected void refreshItems() {

        mAdapter.setRefugees(mRefugeesFiltered);
        mAdapter.notifyDataSetChanged();
    }

    private void initializeData() {

        mRefugees = getIntent().getParcelableArrayListExtra("ListRefugees");

        mRefugeesFiltered = mRefugees;
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

        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent i = new Intent(this, MainMenu.class);
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

                ArrayList<Refugee> filteredList = new ArrayList<>();

                for (Refugee s : mRefugees) {
                    if (!charString.isEmpty()) {
                        if ((s.getName() != null && s.getName().toLowerCase().contains(charString)
                        ) || (s.getSurname1() != null && s.getSurname1().toLowerCase().contains
                                (charString)) || (s.getSurname2() != null && s
                                .getSurname2().toLowerCase().contains(charString)) || (s.getSex()
                                != null && s.getSex().toLowerCase().contains(charString)) || (s
                                .getBirthDate() != null && s.getBirthDate().toLowerCase()
                                .contains(charString))) {

                            filteredList.add(s);
                        }
                    } else {
                        filteredList.add(s);
                    }

                }

                mRefugeesFiltered = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = mRefugeesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mRefugeesFiltered = (ArrayList<Refugee>) filterResults.values;
                refreshItems();
            }
        };
    }
}
