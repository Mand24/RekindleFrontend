package com.example.usuario.rekindlefrontend.view.helpLinks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
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
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.LinksAdapter;
import com.example.usuario.rekindlefrontend.data.entity.link.Link;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHelpLinks extends AppBaseActivity implements Filterable{

    private List<Link> mLinks = new ArrayList<>();
    private List<Link> mFilteredLinks = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinksAdapter mAdapter;
    private SearchView searchView;
    private APIService mAPIService;
    private ImageButton legalFilter, healthFilter, educationFilter;
    private HashMap<String, Boolean> filters = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_help_links);

        getSupportActionBar().setTitle(R.string.links);

        filters.put("Legal", true);
        filters.put("Health", true);
        filters.put("Education", true);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initializeData();

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setAdapterListener();

        recyclerView.setAdapter(mAdapter);

        legalFilter = (ImageButton) findViewById(R.id.button_legal_type);

        legalFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filters.get("Legal")) {
                    filters.put("Legal", false);
                    legalFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Legal", true);
                    legalFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        healthFilter = (ImageButton) findViewById(R.id.button_health_type);

        healthFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filters.get("Health")) {
                    filters.put("Health", false);
                    healthFilter.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Health", true);
                    healthFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });

        educationFilter = (ImageButton) findViewById(R.id.button_education_type);

        educationFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toggle imagen; filtrar()
                if (filters.get("Education")) {
                    filters.put("Education", false);
                    educationFilter.setBackgroundColor(getResources().getColor(R.color.colorIron));
                } else {
                    filters.put("Education", true);
                    educationFilter.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimaryDarker));
                }
                getFilter().filter(searchView.getQuery());
            }
        });
    }

    protected void setAdapterListener() {
        mAdapter = new LinksAdapter(getApplicationContext(), mFilteredLinks,
                new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        String url = mFilteredLinks.get(position).getUrl();
//                        Uri uri = Uri.parse("http://www.andreaardions.com/");
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {

                    }
                });
    }

    protected void refreshItems() {

        mAdapter.setLinks(mFilteredLinks);
        mAdapter.notifyDataSetChanged();
    }

    protected void initializeData() {

        mAPIService.getLinks().enqueue(new Callback<ArrayList<Link>>() {
            @Override
            public void onResponse(Call<ArrayList<Link>> call, Response<ArrayList<Link>> response) {
                if (response.isSuccessful()){
                    manageResult(true, response.body());
                }
                else {
                    manageResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Link>> call, Throwable t) {
                manageResult(false, null);
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

                ArrayList<Link> filteredList = new ArrayList<>();

                for (Link s : mLinks) {
                    if (filters.get(s.getType())) {
                        if (!charString.isEmpty()) {
                            if (s.getUrl().toLowerCase().contains(charString) || s
                                    .getDescription().toLowerCase().contains(charString)) {

                                filteredList.add(s);
                            }
                        } else {
                            filteredList.add(s);
                        }
                    }
                }

                mFilteredLinks = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredLinks;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredLinks = (ArrayList<Link>) filterResults.values;
                refreshItems();
            }
        };
    }

    public void manageResult(boolean result, List<Link> body) {

        if (result) {
            mLinks = body;
            mFilteredLinks = mLinks;
            refreshItems();

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }


}
