package com.example.usuario.rekindlefrontend.view.moderate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;

public class EnableUser extends AppCompatActivity {

    private TextView userType;
    private TextView name;
    private TextView surname1;
    private TextView surname2;
    private TextView email;
    private ImageView photoUser;

    private APIService mAPIService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_user);

        setViews();
    }

    void setViews(){
        userType = findViewById(R.id.tipo_usuario_perfil_voluntario);
        name = findViewById(R.id.nombre_usuario_perfil_voluntario);
        surname1 = findViewById(R.id.apellido1_usuario_perfil_voluntario);
        surname2 = findViewById(R.id.apellido2_usuario_perfil_voluntario);
        email = findViewById(R.id.email_usuario_perfil_voluntario);
        photoUser = findViewById(R.id.foto_perfil_voluntario);

        mAPIService = APIUtils.getAPIService();
    }
}
