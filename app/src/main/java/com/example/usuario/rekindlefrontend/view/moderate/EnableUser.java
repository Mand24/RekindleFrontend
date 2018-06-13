package com.example.usuario.rekindlefrontend.view.moderate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnableUser extends AppCompatActivity {

    private TextView userType;
    private TextView name;
    private TextView surname1;
    private TextView surname2;
    private TextView email;
    private ImageView photoUser;
    private AppCompatButton disable;
    private EditText motive;

    private APIService mAPIService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_user);

        setViews();

        user = getIntent().getParcelableExtra("User");
        initializeFields();

        disable.setClickable(false);

        mAPIService.isUserEnabled(user.getMail()).enqueue(

                new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        System.out.println("CODIGOisenabled? " + response.code());
                        if (response.isSuccessful()) {
                            disable.setClickable(true);
                            if (response.body() == 1) {
                                disable.setText(R.string.deshabilitar);
                            } else {
                                disable.setText(R.string.habilitar);
                                motive.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            System.out.println("CODIGO " + response.code());
                            failure();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("on Failure", t.toString());
                        failure();
                    }
                });

        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (disable.getText().toString().equals(getResources().getString(R
                        .string.deshabilitar))) {
                    mAPIService.disableUser(user.getMail(), motive.getText().toString()).enqueue
                            (new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("CODIGOdisable " + response.code());
                            if (response.isSuccessful()) {
                                disable.setText(R.string.habilitar);
                                motive.setVisibility(View.INVISIBLE);
                            } else {
                                failure();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            failure();
                        }
                    });
                } else {
                    mAPIService.enableUser(user.getMail()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("CODIGOenable " + response.code());
                            if (response.isSuccessful()) {
                                disable.setText(R.string.deshabilitar);
                                motive.setVisibility(View.VISIBLE);
                            } else {
                                failure();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            failure();
                        }
                    });
                }
            }
        });
    }

    void setViews(){
        userType = findViewById(R.id.tipo_usuario);
        name = findViewById(R.id.nombre_usuario);
        surname1 = findViewById(R.id.apellido1_usuario);
        surname2 = findViewById(R.id.apellido2_usuario);
        email = findViewById(R.id.email_usuario);
        photoUser = findViewById(R.id.foto);
        disable = findViewById(R.id.enable_disable);
        motive = findViewById(R.id.motive);

        mAPIService = APIUtils.getAPIService();
    }

    public void initializeFields() {

        userType.setText("Refugiado");
        name.setText(user.getName());
        surname1.setText(user.getSurname1());
        surname2.setText(user.getSurname2());
        email.setText(user.getMail());
        if (user.getPhoto() != null) {
            photoUser.setImageBitmap(user.getDecodedPhoto());
        } else {
            photoUser.setImageResource(R.drawable.ic_usuario);
        }
    }

    private void failure() {
        Toast.makeText(this, getResources().getString(R
                .string.error), Toast.LENGTH_SHORT).show();
    }
}
