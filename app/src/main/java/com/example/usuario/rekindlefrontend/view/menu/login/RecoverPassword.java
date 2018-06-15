package com.example.usuario.rekindlefrontend.view.menu.login;

import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecoverPassword extends AppCompatActivity {

    Button _changePassword;
    EditText _passwordText;
    EditText _confirmPasswordText;
    EditText _codeText;
    TextView _back;

    private String code;
    private String password;
    private String confirmPassword;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

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

                setParams();

                if (!code.isEmpty() && codeSystem.equals(code)) {
                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.password_distinta),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        sendRecoverPassword(email, password);

                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.wrong_code),
                            Toast.LENGTH_SHORT).show();
                }
            }

            public void sendRecoverPassword(String email, String password) {
                mAPIService.recuperarPassword(email, password).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            System.out.println("CODI1 " + response.code());
                            manageResultRequest(true);
                        } else {
                            System.out.println("CODI1 " + response.code());
                            manageResultRequest(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.network_fail), Toast
                                            .LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.conversation_fail),
                                    Toast
                                            .LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }

            public void manageResultRequest(boolean result) {
                if (result) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.password_actualizada),
                            Toast.LENGTH_SHORT).show();
                    sendLogin(email, _passwordText.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.password_fail),
                            Toast.LENGTH_SHORT).show();
                }
            }

            public void sendLogin(String email, String password) {
                System.out.println("EMAIL " + email);
                mAPIService.login(email, password).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            manageResultLogin(true, user);
                        } else {
                            System.out.println("CODI2 " + response.code());
                            manageResultLogin(false, response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.network_fail), Toast
                                            .LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.conversation_fail),
                                    Toast
                                            .LENGTH_SHORT)
                                    .show();

                        }

                    }
                });
            }

            public void manageResultLogin(boolean result, User user) {
                if (result) {
                    System.out.println("USUARIO1 " + user.toString());
                    saveUser(user, getApplicationContext());
                    System.out.println("USUARIO2 " + user.toString());
                    Intent i = new Intent(getApplicationContext(), MainMenu.class);
                    System.out.println("TIPO " + user.getUserType());
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.login_fail),
                            Toast.LENGTH_LONG).show();
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

    public void setParams() {
        code = _codeText.getText().toString();
        password = _passwordText.getText().toString();
        confirmPassword = _confirmPasswordText.getText().toString();
    }

    public String getCode () { return code; }
    public String getPassword () { return password; }
    public String getConfirmPassword () { return confirmPassword; }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
