package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.view.CreateReport;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;

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

    private AppCompatButton reportar;

    private APIService mAPIService;
    private Refugiado refugiado;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mostrar_perfil_refugiado);

        setVistas();

        refugiado = (Refugiado) getIntent().getParcelableExtra("Refugiado");
        System.out.println(refugiado.toString());

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
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
