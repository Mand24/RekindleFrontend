package com.example.usuario.rekindlefrontend.view.moderate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.UsersAdapter;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsers extends AppBaseActivity  implements Filterable {

    protected List<User> mUsers = new ArrayList<>();
    protected List<User> mUsersFiltered = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected UsersAdapter mAdapter;
    protected SearchView searchView;
    protected APIService mAPIService;
    protected ImageButton enabledFilter, disabledFilter;
    protected HashMap<String, Boolean> filters = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        filters.put("Enable", true);
        filters.put("Disable", true);
        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        getSupportActionBar().setTitle(R.string.listUsers);

        initializeData();

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setAdapterListener();

        recyclerView.setAdapter(mAdapter);

        enabledFilter = findViewById(R.id.enabled);

        enabledFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Enable")) {
                    filters.put("Enable", false);
                    enabledFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Enable", true);
                    enabledFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        disabledFilter = findViewById(R.id.disabled);

        disabledFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Disable")) {
                    filters.put("Disable", false);
                    disabledFilter.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Disable", true);
                    disabledFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });
    }

    protected void setAdapterListener() {
        mAdapter = new UsersAdapter(getApplicationContext(), mUsersFiltered,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(),
                                EnableUser.class);
                        intent.putExtra("User", mUsers.get(position));
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }

    protected void refreshItems() {

        mAdapter.setUsers(mUsersFiltered);
        mAdapter.notifyDataSetChanged();
    }

    private void initializeData() {

        mAPIService.getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()){
                    manageResult(true, response.body());
                }
                else {
                    manageResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                manageResult(false, null);
            }
        });
    }

    private void manageResult(boolean result, List<User> body) {

        if (result) {
            mUsers = body;
            mUsersFiltered = mUsers;
            refreshItems();

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
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

                ArrayList<User> filteredList = new ArrayList<>();

                for (User s : mUsers) {
                    if (filters.get(s.getUserType())) {
                        if (!charString.isEmpty()) {
                            if ((s.getName() != null && s.getName().toLowerCase().contains(charString)
                            ) || (s.getSurname1() != null && s.getSurname1().toLowerCase().contains
                                    (charString)) || (s.getSurname2() != null && s
                                    .getSurname2().toLowerCase().contains(charString)) || (s.getUserType()
                                    != null && s.getUserType().toLowerCase().contains(charString))) {

                                filteredList.add(s);
                            }
                        } else {
                            filteredList.add(s);
                        }
                    }
                }

                mUsersFiltered = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = mUsersFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mUsersFiltered = (ArrayList<User>) filterResults.values;
                refreshItems();
            }
        };
    }
}
