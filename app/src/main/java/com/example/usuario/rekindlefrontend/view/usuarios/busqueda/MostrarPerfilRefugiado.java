package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.usuarios.chat.ShowChat;
import com.google.gson.Gson;

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

    private APIService mAPIService;
    private Refugiado refugiado;
    private Usuario currentUser;
    private Chat newChat;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar_perfil_refugiado);

        setVistas();

        refugiado = (Refugiado) getIntent().getParcelableExtra("Refugiado");
        currentUser = getUser(getApplicationContext());
        System.out.println(refugiado.toString());

        llenarTextViews();

        buttonChat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sendGetChat();
            }
        });


    }

    public void setVistas(){
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

        tipoUsuario.setText("Refugiado");
        nombreUsuario.setText(refugiado.getName());
        apellido1.setText(refugiado.getSurname1());
        apellido2.setText(refugiado.getSurname2());
        emailUsuario.setText(refugiado.getMail());
        telefonoUsuario.setText(refugiado.getPhoneNumber());
        nacimientoUsuario.setText(refugiado.getBirthDate());
        sexoUsuario.setText(refugiado.getSex());
        paisUsuario.setText(refugiado.getCountry());
        puebloUsuario.setText(refugiado.getTown());
        etniaUsuario.setText(refugiado.getEthnic());
        sangreUsuario.setText(refugiado.getBloodType());
        ojosUsuario.setText(refugiado.getEyeColor());
        biografiaUsuario.setText(refugiado.getBiography());

    }

    public void sendGetChat(){
        mAPIService.getChat(currentUser.getMail(), currentUser.getMail(), refugiado.getMail()).enqueue(
                new Callback<Chat>() {
                    @Override
                    public void onResponse(Call<Chat> call, Response<Chat> response) {
                        if (response.isSuccessful()){
                            tratarResultadoPeticion(true, response.body());
                        }
                        else {
                            tratarResultadoPeticion(false, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Chat> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R
                                .string.error), Toast.LENGTH_SHORT).show();
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
            newChat = new Chat(currentUser,refugiado);
            sendNewChat(newChat);
        }
    }

    public void sendNewChat(Chat newChat){
        mAPIService.newChat(currentUser.getMail(), newChat).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    manageResult(true);
                }
                else {
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                manageResult(false);
            }
        });

    }

    public void manageResult(boolean result){
        if (result){
            Intent i = new Intent(this, ShowChat.class);
            i.putExtra("Chat", newChat);
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
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
