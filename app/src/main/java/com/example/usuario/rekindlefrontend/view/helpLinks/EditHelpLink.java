package com.example.usuario.rekindlefrontend.view.helpLinks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.link.Link;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditHelpLink extends AppBaseActivity {

    private Spinner type;
    ArrayAdapter<CharSequence> adapter;
    private EditText url, description;
    private Link link;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_help_link);

        link = getIntent().getParcelableExtra("Link");

        setViews();

        setFields();

        AppCompatButton send_link = findViewById(R.id.send_link);
        send_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateLink();
                sendUpdateLink();
            }

        });
    }

    public void setViews() {
        type = findViewById(R.id.type);

        adapter = ArrayAdapter.createFromResource(this, R.array.list_type_links, R.layout
                .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        type.setAdapter(adapter);
        url = findViewById(R.id.url);
        description = findViewById(R.id.description);
        mAPIService = APIUtils.getAPIService();
    }

    public void setFields(){
        int selectionPosition = adapter.getPosition(link.getType());
        type.setSelection(selectionPosition);
        url.setText(link.getUrl());
        description.setText(link.getDescription());
    }

    public void updateLink() {
        String linkType;
        if (type.getSelectedItemPosition() == 0){
            linkType = "Legal";
        } else if (type.getSelectedItemPosition() == 1) {
            linkType = "Health";
        } else {
            linkType = "Education";
        }
        link.setType(linkType);
        link.setUrl(url.getText().toString());
        link.setDescription(description.getText().toString());
    }

    public void sendUpdateLink() {
        System.out.println(link.toString());
        mAPIService.updateLink(Consistency.getUser(this).getApiKey(), link.getIdLink(), link)
                .enqueue(new
                                                                                             Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("codi "+ response.code());
                if (response.isSuccessful()) {
                    manageResult(true);
                } else {
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                manageResult(false);
            }
        });
    }

    public void manageResult(boolean result) {

        if (result) {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.link_created_successfully), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), ListHelpLinksAdmin.class);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
