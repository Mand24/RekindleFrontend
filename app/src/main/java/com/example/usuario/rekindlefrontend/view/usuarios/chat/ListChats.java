package com.example.usuario.rekindlefrontend.view.usuarios.chat;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

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

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.ChatsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChats extends AppBaseActivity implements Filterable {

    protected List<Chat> chats = new ArrayList<>();
    protected List<Chat> filteredChats = new ArrayList<>();
    protected RecyclerView recyclerView;
    protected ChatsAdapter mAdapter;
    protected SearchView searchView;
    protected APIService mAPIService;
    protected Usuario currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chats);

        mAPIService = APIUtils.getAPIService();
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

    private void initializeData() {
        currentUser = getUser(getApplicationContext());
        mAPIService.getChats(currentUser.getMail()).enqueue(new Callback<ArrayList<Chat>>() {
            @Override
            public void onResponse(Call<ArrayList<Chat>> call, Response<ArrayList<Chat>> response) {
                if (response.isSuccessful()){
                    tratarResultadoPeticion(true, response.body());
                }else {
                    tratarResultadoPeticion(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chat>> call, Throwable t) {
                tratarResultadoPeticion(false, null);
            }
        });
    }

    public void tratarResultadoPeticion(boolean result, List<Chat> respuesta) {

        if (result) {
            chats = respuesta;
            filteredChats = chats;
            //refreshItems(); no hauria de fer falta

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapterListener() {
        mAdapter = new ChatsAdapter(filteredChats, new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                Intent intent = new Intent(getApplicationContext(), ShowChat.class);
                                intent.putExtra("Chat", chats.get(position));
                                startActivity(intent);
                            }
                            @Override
                            public void onItemLongClick(View v, int position) {
                            }
                        },
                getApplicationContext());
    }

    protected void refreshItems() {

        mAdapter.setChats(filteredChats);
        mAdapter.notifyDataSetChanged();
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

                ArrayList<Chat> filteredList = new ArrayList<>();

                for (Chat s : chats) {
                    Usuario user;
                    if (!s.getUser1().getMail().equals(currentUser.getMail())){
                        user = s.getUser1();
                    }else {
                        user = s.getUser2();
                    }
                    if(!charString.isEmpty()) {
                        if ((user.getName() != null && user.getName().toLowerCase().contains
                                (charString)
                        )|| (user.getSurname1() != null && user.getSurname1().toLowerCase().contains
                                (charString))) {

                            filteredList.add(s);
                        }
                    }
                    else{
                        filteredList.add(s);
                    }

                }

                filteredChats = filteredList;

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredChats;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredChats = (ArrayList<Chat>) filterResults.values;
                refreshItems();
            }
        };
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
