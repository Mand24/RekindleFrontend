package com.example.usuario.rekindlefrontend.view.usuarios.chat;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

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
import com.example.usuario.rekindlefrontend.adapters.ChatsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MainMenu;

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
    protected User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chats);

        mAPIService = APIUtils.getAPIService();
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        getSupportActionBar().setTitle(R.string.listChat);

        initializeData();

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
                System.out.println("listchat code: " + response.code());
                if (response.isSuccessful()){
                    manageResult(true, response.body());
                }else {
                    manageResult(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chat>> call, Throwable t) {
                manageResult(false, null);
            }
        });
    }

    public void manageResult(boolean result, List<Chat> respuesta) {

        if (result) {
            chats = respuesta;
            filteredChats = chats;
            refreshItems(); //no hauria de fer falta

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
                                intent.putExtra("Chat", filteredChats.get(position));
                                System.out.println(filteredChats.get(position).toString());
                                System.out.println("Llista");
                                for (Chat s : filteredChats){
                                    System.out.println(s.toString());
                                }
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

                ArrayList<Chat> filteredList = new ArrayList<>();

                for (Chat s : chats) {
                    User user;
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
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
