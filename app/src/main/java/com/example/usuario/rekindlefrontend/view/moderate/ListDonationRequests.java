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
import com.example.usuario.rekindlefrontend.adapters.DonationRequestsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
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

public class ListDonationRequests extends AppBaseActivity implements Filterable {

    protected SearchView searchView;
    protected List<DonationRequest> donationRequests = new ArrayList<>();
    protected List<DonationRequest> filteredDonationRequests = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected APIService mAPIService;
    protected DonationRequestsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donation_requests);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        getSupportActionBar().setTitle(R.string.list_requests);
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
        mAdapter = new DonationRequestsAdapter(getApplicationContext(), filteredDonationRequests,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(),
                                ShowDonationRequest.class);
                        intent.putExtra("Request", filteredDonationRequests.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }

    private void initializeData() {

        mAPIService.getDonationRequests().enqueue(new Callback<ArrayList<DonationRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<DonationRequest>> call,
                    Response<ArrayList<DonationRequest>> response) {
                if (response.isSuccessful()) {
                    manageResult(response.body());
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R
                            .string.error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DonationRequest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R
                        .string.error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void manageResult(ArrayList<DonationRequest> listDonationRequests) {
        donationRequests = listDonationRequests;
        filteredDonationRequests = donationRequests;
        refreshItems();
    }

    private void refreshItems() {
        mAdapter.setDonationRequests(filteredDonationRequests);
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

    private void search(android.support.v7.widget.SearchView searchView) {

        searchView.setOnQueryTextListener(
                new android.support.v7.widget.SearchView.OnQueryTextListener() {
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

                ArrayList<DonationRequest> filteredList = new ArrayList<>();

                for (DonationRequest s : donationRequests) {

                    if (!charString.isEmpty()) {
                        if ((s.getRefugeeMail() != null
                                && s.getRefugeeMail().toLowerCase().contains
                                (charString)
                        ) || (s.getDonation().getName() != null
                                && s.getDonation().getName().toLowerCase()
                                .contains
                                        (charString))) {

                            filteredList.add(s);
                        }
                    } else {
                        filteredList.add(s);
                    }

                }

                filteredDonationRequests = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDonationRequests;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDonationRequests = (ArrayList<DonationRequest>) filterResults.values;
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
