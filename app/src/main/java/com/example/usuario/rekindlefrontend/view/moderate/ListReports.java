package com.example.usuario.rekindlefrontend.view.moderate;

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
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ReportsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.misc.Report;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListReports extends AppBaseActivity implements Filterable {

    protected SearchView searchView;
    protected List<Report> reports = new ArrayList<>();
    protected List<Report> filteredReports = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected APIService mAPIService;
    protected ReportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);


        getSupportActionBar().setTitle(R.string.listar_reportes);
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
        mAdapter = new ReportsAdapter(getApplicationContext(), filteredReports,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), ShowReport.class);
                        intent.putExtra("Report", filteredReports.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }

    private void initializeData() {

        mAPIService.getReports().enqueue(new Callback<ArrayList<Report>>() {
            @Override
            public void onResponse(Call<ArrayList<Report>> call,
                    Response<ArrayList<Report>> response) {
                if (response.isSuccessful()) {
                    manageResult(response.body());
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R
                            .string.error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Report>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R
                        .string.error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void manageResult(ArrayList<Report> listReports) {
        reports = listReports;
        filteredReports = reports;
        refreshItems();
    }

    private void refreshItems() {
        mAdapter.setReports(filteredReports);
        mAdapter.notifyDataSetChanged();
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

                ArrayList<Report> filteredList = new ArrayList<>();

                for (Report s : reports) {

                    if (!charString.isEmpty()) {
                        if ((s.getInformerUserMail() != null
                                && s.getInformerUserMail().toLowerCase().contains
                                (charString)
                        ) || (s.getReportedUserMail() != null
                                && s.getReportedUserMail().toLowerCase()
                                .contains
                                        (charString))) {

                            filteredList.add(s);
                        }
                    } else {
                        filteredList.add(s);
                    }

                }

                filteredReports = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredReports;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredReports = (ArrayList<Report>) filterResults.values;
                refreshItems();
            }
        };
    }

    public void onBackPressed() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
