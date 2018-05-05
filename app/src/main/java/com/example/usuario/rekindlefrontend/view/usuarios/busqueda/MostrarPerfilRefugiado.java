package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarPerfilRefugiado extends AppCompatActivity {

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

    private APIService mAPIService;
    private Refugiado refugiado;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar_perfil_refugiado);

        setVistas();
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
        mAPIService = APIUtils.getAPIService();

        sendObtenerRefugiado();
    }

    public void sendObtenerRefugiado(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        Usuario usuario = gson.fromJson(json, Usuario.class);
        System.out.println("tipo app: "+ usuario.getMail());
        String mail = usuario.getMail();

        mAPIService.obtenerRefugiado(mail).enqueue(new Callback<Refugiado>() {
            @Override
            public void onResponse(Call<Refugiado> call, Response<Refugiado> response) {
                if (response.isSuccessful()){
                    System.out.println("dentro respuesta");
                    if (response.body() != null) System.out.println("dentro respuesta ok");
                    refugiado = response.body();
                    refugiado.setTipo(0);
                    tratarResultadoPeticion(true);
                }
                else {
                    System.out.println("refugiado null");
                    System.out.println("Mensaje: "+response.message());
                    System.out.println("codi: "+response.code());
                    System.out.println("dentro respuesta failed");
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<Refugiado> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "this is an actual network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            llenarTextViews();

            Toast.makeText(getApplicationContext(), "ver perfil correctamente",
                    Toast
                            .LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "ver perfil fallida", Toast
                    .LENGTH_SHORT).show();
        }
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        i.putExtra("tipo", 0);
        startActivity(i);
    }
}
