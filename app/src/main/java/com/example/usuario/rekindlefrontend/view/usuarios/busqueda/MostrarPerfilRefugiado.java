package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.AppBaseActivity;

import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;

import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.view.CreateReport;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import com.example.usuario.rekindlefrontend.view.usuarios.chat.ShowChat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostrarPerfilRefugiado extends AppBaseActivity {

    private TextView tipoUsuario;
    private TextView nombreUsuario;
    private TextView apellido1;
    private TextView apellido2;
    private TextView emailUsuario;
    private TextView telefonoUsuario;
    private TextView nacimientoUsuario;
    private TextView sexoUsuario;
    private TextView paisUsuario;
    private TextView puebloUsuario;
    private TextView etniaUsuario;
    private TextView sangreUsuario;
    private TextView ojosUsuario;
    private TextView biografiaUsuario;


    private AppCompatButton buttonChat;

    private AppCompatButton reportar;


    private APIService mAPIService;
    private Refugee mRefugee;
    private User currentUser;
    private Chat newChat;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar_perfil_refugiado);

        getSupportActionBar().setTitle(R.string.showRefugee);

        setVistas();

        mRefugee = (Refugee) getIntent().getParcelableExtra("Refugee");
        currentUser = getUser(getApplicationContext());
        System.out.println(mRefugee.toString());

        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateReport
                        .class);
                i.putExtra("ReportedUser", refugiado);
                startActivity(i);
            }
        });

        llenarTextViews();

        buttonChat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Chat chat = new Chat(currentUser, mRefugee);
                sendGetChat();

            }
        });


    }

    public void setVistas(){
        reportar = findViewById(R.id.boton_reportar);
        tipoUsuario = findViewById(R.id.tipo_usuario_perfil_refugiado);
        nombreUsuario = findViewById(R.id.nombre_usuario_perfil_refugiado);
        apellido1 = findViewById(R.id.apellido1_usuario_perfil_refugiado);
        apellido2 = findViewById(R.id.apellido2_usuario_perfil_refugiado);
        emailUsuario = findViewById(R.id.email_usuario_perfil_refugiado);
        telefonoUsuario = findViewById(R.id.telefono_usuario_perfil_refugiado);
        nacimientoUsuario = findViewById(R.id.naciminento_usuario_perfil_refugiado);
        sexoUsuario = findViewById(R.id.sexo_usuario_perfil_refugiado);
        paisUsuario = findViewById(R.id.pais_usuario_perfil_refugiado);
        puebloUsuario = findViewById(R.id.pueblo_usuario_perfil_refugiado);
        etniaUsuario = findViewById(R.id.etnia_usuario_perfil_refugiado);
        sangreUsuario = findViewById(R.id.sangre_usuario_perfil_refugiado);
        ojosUsuario = findViewById(R.id.ojos_usuario_perfil_refugiado);
        biografiaUsuario = findViewById(R.id.biografia_usuario_perfil_refugiado);

        buttonChat = findViewById(R.id.chat);
        mAPIService = APIUtils.getAPIService();

    }


    public void llenarTextViews(){

        tipoUsuario.setText("Refugee");
        nombreUsuario.setText(mRefugee.getName());
        apellido1.setText(mRefugee.getSurname1());
        apellido2.setText(mRefugee.getSurname2());
        emailUsuario.setText(mRefugee.getMail());
        telefonoUsuario.setText(mRefugee.getPhoneNumber());
        nacimientoUsuario.setText(mRefugee.getBirthDate());
        sexoUsuario.setText(mRefugee.getSex());
        paisUsuario.setText(mRefugee.getCountry());
        puebloUsuario.setText(mRefugee.getTown());
        etniaUsuario.setText(mRefugee.getEthnic());
        sangreUsuario.setText(mRefugee.getBloodType());
        ojosUsuario.setText(mRefugee.getEyeColor());
        biografiaUsuario.setText(mRefugee.getBiography());

    }

    public void sendGetChat(){
        mAPIService.getChat(currentUser.getMail(), currentUser.getMail(), mRefugee.getMail()).enqueue(
                new Callback<Chat>() {
                    @Override
                    public void onResponse(Call<Chat> call, Response<Chat> response) {
                        System.out.println("getchat code: " + response.code());
                        if (response.isSuccessful()){
                            System.out.println("getchat");
                            System.out.println(response.body().toString());
                            tratarResultadoPeticion(true, response.body());
                        }
                        else {
                            tratarResultadoPeticion(false, null);
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

    public void tratarResultadoPeticion(boolean resultado, Chat chat){
        if (resultado){
            Intent i = new Intent(this, ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
        }
        else {
            newChat = new Chat(currentUser, mRefugee);
            sendNewChat(newChat);
        }
    }

    public void sendNewChat(Chat newChat){
        mAPIService.newChat(currentUser.getMail(), newChat).enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                System.out.println("newchat code: " + response.code());
                if (response.isSuccessful()){
                    System.out.println("newchat");
                    System.out.println(response.body().toString());
                    manageResult(true, response.body());
                }
                else {
                    manageResult(false, null);
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

    public void manageResult(boolean result, Chat chat){
        if (result){
            Intent i = new Intent(this, ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
        }
        else {
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
