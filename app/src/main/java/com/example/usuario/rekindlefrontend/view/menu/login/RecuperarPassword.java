package com.example.usuario.rekindlefrontend.view.menu.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarPassword extends AppCompatActivity {

    Button _changePassword;
    EditText _passwordText;
    EditText _confirmPasswordText;
    EditText _codeText;
    TextView _back;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        mAPIService = APIUtils.getAPIService();

        final String email = getIntent().getExtras().getString("email");
        final String codeSystem = getIntent().getExtras().getString("code");

        _passwordText = findViewById(R.id.contrasena);
        _confirmPasswordText = findViewById(R.id.confirmar_contrasena);
        _codeText = findViewById(R.id.codigo);
        _changePassword = findViewById(R.id.cambiar_contrasena);

        _changePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String code = _codeText.getText().toString();
                String password = _passwordText.getText().toString();
                String confirmPassword = _confirmPasswordText.getText().toString();

                if(!code.isEmpty() && codeSystem.equals(code)) {
                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(getApplicationContext(), getString (R.string.contraseña_distinta),
                                Toast.LENGTH_SHORT).show();
                    } else {
//                        boolean result = true;
//                        result = new AsyncTaskCall().execute(email, password).get();
                        sendRecuperarPassword(email, password);

                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            getString (R.string.wrong_code),
                            Toast.LENGTH_SHORT).show();
                }
            }

            public void sendRecuperarPassword(String email, String password){
                mAPIService.recuperarPassword(email, password).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            System.out.println("CODI1 "+response.code());
                            tratarResultadoRecuperarPassword(true);
                        }
                        else {
                            System.out.println("CODI1 "+response.code());
                            tratarResultadoRecuperarPassword(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getApplicationContext(), getString (R.string.network_fail), Toast
                                    .LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(), getString (R.string.conversation_fail),
                                    Toast
                                    .LENGTH_SHORT)
                                    .show();

                        }
                    }
                });
            }

            public void tratarResultadoRecuperarPassword(boolean result){
                if (result) {
                    Toast.makeText(getApplicationContext(), getString (R.string.contraseña_actualizada),
                            Toast.LENGTH_SHORT).show();
                    sendLogin(email, _passwordText.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString (R.string.contraseña_fail),
                            Toast.LENGTH_SHORT).show();
                }
            }
            public void sendLogin(String email, String password){
                System.out.println("EMAIL "+email);
                mAPIService.login(email, password).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()){
                            /*System.out.println("CODI2 "+response.code());
                            System.out.println("BODY "+response.body().toString());
                            String header1 = response.headers().get("Tipo");
                            int i = Integer.parseInt(header1);*/
                            Usuario usuario = response.body();
//                            usuario.setServiceType(i);
                            tratarResultadoLogin(true, usuario);
                        }else {
                            System.out.println("CODI2 "+response.code());
                            tratarResultadoLogin(false, response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getApplicationContext(), getString (R.string.network_fail), Toast
                                    .LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(), getString (R.string.conversation_fail),
                                    Toast
                                            .LENGTH_SHORT)
                                    .show();

                        }

                    }
                });
            }

            public void tratarResultadoLogin(boolean result, Usuario usuario){
                if (result){
                    System.out.println("USUARIO1 "+usuario.toString());
                    /*SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor miEditor = datos.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(usuario);
                    miEditor.putString("usuario", json);
                    miEditor.apply();*/

                    saveUser(usuario, getApplicationContext());

                    System.out.println("USUARIO2 "+usuario.toString());
                    Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                    System.out.println("TIPO "+usuario.getTipo());
                    startActivity(i);
                }
                else {
                    Toast.makeText(getBaseContext(), getString (R.string.login_fail), Toast.LENGTH_LONG).show();
                }
            }


        });

        _back = findViewById(R.id.back_to_menu);
        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CodePasswordRequest.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
