package com.example.usuario.rekindlefrontend.view.users.search;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.chat.ShowChat;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.moderate.CreateReport;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRefugee extends AppBaseActivity {

    private TextView userType;
    private TextView name;
    private TextView surname1;
    private TextView surname2;
    private TextView email;
    private TextView phoneNumber;
    private TextView birthday;
    private TextView sex;
    private TextView country;
    private TextView town;
    private TextView ethnic;
    private TextView blood;
    private TextView eyes;
    private TextView biography;
    private ImageView photoUser;

    private AppCompatButton buttonReport, buttonChat;

    private APIService mAPIService;
    private Refugee refugee;
    private User currentUser;
    private Chat newChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_refugee);

        getSupportActionBar().setTitle(R.string.showRefugee);

        setViews();

        refugee = (Refugee) getIntent().getParcelableExtra("Refugee");
        currentUser = getUser(getApplicationContext());
        System.out.println(refugee.toString());

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateReport
                        .class);
                i.putExtra("ReportedUser", refugee);
                startActivity(i);
            }
        });

        initializeFields();

        buttonChat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Chat chat = new Chat(currentUser,refugee);
                sendGetChat();
            }
        });


    }

    public void setViews() {
        buttonReport = findViewById(R.id.boton_reportar);
        userType = findViewById(R.id.tipo_usuario_perfil_refugiado);
        name = findViewById(R.id.nombre_usuario_perfil_refugiado);
        surname1 = findViewById(R.id.apellido1_usuario_perfil_refugiado);
        surname2 = findViewById(R.id.apellido2_usuario_perfil_refugiado);
        email = findViewById(R.id.email_usuario_perfil_refugiado);
        phoneNumber = findViewById(R.id.telefono_usuario_perfil_refugiado);
        birthday = findViewById(R.id.naciminento_usuario_perfil_refugiado);
        sex = findViewById(R.id.sexo_usuario_perfil_refugiado);
        country = findViewById(R.id.pais_usuario_perfil_refugiado);
        town = findViewById(R.id.pueblo_usuario_perfil_refugiado);
        ethnic = findViewById(R.id.etnia_usuario_perfil_refugiado);
        blood = findViewById(R.id.sangre_usuario_perfil_refugiado);
        eyes = findViewById(R.id.ojos_usuario_perfil_refugiado);
        biography = findViewById(R.id.biografia_usuario_perfil_refugiado);
        photoUser = findViewById(R.id.foto_perfil_refugiado);
        buttonChat = findViewById(R.id.chat);
        mAPIService = APIUtils.getAPIService();

    }


    public void initializeFields() {

        userType.setText("Refugiado");
        name.setText(refugee.getName());
        surname1.setText(refugee.getSurname1());
        surname2.setText(refugee.getSurname2());
        email.setText(refugee.getMail());
        phoneNumber.setText(refugee.getPhoneNumber());
        birthday.setText(refugee.getBirthDate());
        sex.setText(refugee.getSex());
        country.setText(refugee.getCountry());
        town.setText(refugee.getTown());
        ethnic.setText(refugee.getEthnic());
        blood.setText(refugee.getBloodType());
        eyes.setText(refugee.getEyeColor());
        biography.setText(refugee.getBiography());
        if (refugee.getPhoto() != null) {
            photoUser.setImageBitmap(refugee.getDecodedPhoto());
        } else {
            photoUser.setImageResource(R.drawable.ic_usuario);
        }

    }

    public void sendGetChat() {
        mAPIService.getChat(currentUser.getMail(), currentUser.getMail(),
                refugee.getMail()).enqueue(
                new Callback<Chat>() {
                    @Override
                    public void onResponse(Call<Chat> call, Response<Chat> response) {
                        System.out.println("getchat code: " + response.code());
                        if (response.isSuccessful()) {
                            System.out.println("getchat");
                            System.out.println(response.body().toString());
                            manageResultGetChat(true, response.body());
                        } else {
                            manageResultGetChat(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Chat> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getApplicationContext(),
                                    "this is an actual network failure"
                                            + " :( inform "
                                            + "the user and "
                                            + "possibly retry", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "getchat!! conversion issue! big problems :(", Toast
                                            .LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void manageResultGetChat(boolean result, Chat chat) {
        if (result) {
            Intent i = new Intent(this, ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
        } else {
            newChat = new Chat(currentUser, refugee);
            sendNewChat(newChat);
        }
    }

    public void sendNewChat(Chat newChat) {
        mAPIService.newChat(currentUser.getApiKey(), currentUser.getMail(), newChat).enqueue(new
                                                                                        Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                System.out.println("newchat code: " + response.code());
                if (response.isSuccessful()) {
                    System.out.println("newchat");
                    System.out.println(response.body().toString());
                    manageResultNewChat(true, response.body());
                } else {
                    manageResultNewChat(false, null);
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "newchat!! conversion issue! big problems :(", Toast.LENGTH_SHORT)
                            .show();

                }
            }
        });

    }

    public void manageResultNewChat(boolean result, Chat chat) {
        if (result) {
            Intent i = new Intent(this, ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
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
}