package com.example.usuario.rekindlefrontend.view.menu.login;

import static com.example.usuario.rekindlefrontend.data.pusher.Comm.connectPusher;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm
        .setAllChannelsNotificationsChats;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm
        .setAllChannelsNotificationsServices;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setChannelUserChat;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setChannelUserService;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpChannelUser;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpChannelsChats;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpChannelsServices;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpPusher;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.app.ProgressDialog;
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
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.mainMenu.MainMenu;
import com.example.usuario.rekindlefrontend.view.users.register.RegisterUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    EditText _emailText;
    EditText _passwordText;
    TextView _signupLink;
    TextView _recuperarPasswordLink;
    Button _loginButton;
    private int backpress = 0;
    private APIService mAPIService;
    private User mUser;

    private void bind() {
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        _recuperarPasswordLink = (TextView) findViewById(R.id.link_recuperar_password);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);

        mAPIService = APIUtils.getAPIService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        setContentView(R.layout.activity_login);

        bind();


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent i = new Intent(getApplicationContext(), RegisterUser.class); //HERE
                startActivity(i);
            }
        });

        _recuperarPasswordLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CodePasswordRequest.class);
                startActivity(i);
            }
        });
    }

    public void checkLogin() {
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("User", "");
        if (json.isEmpty()) {
            return;
        } else {
            mUser = gson.fromJson(json, User.class);
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
        }
    }

    public void login() {

        if (!validate()) {
            onLoginFailed(0);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.autentificacion));
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        sendLogin();
                        progressDialog.dismiss();
                    }
                }, 1500);
    }

    public void sendLogin() {
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        mAPIService.login(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                /*Set<String> headers = response.headers().names();
                for(String header : headers) {
                    System.out.println("cabecera: "+header);
                }*/

                System.out.println(response.code());
                System.out.println(call.request().url());

                if (response.isSuccessful()) {
                    mUser = response.body();
                    System.out.println(mUser.toString());
                    onLoginSuccess();
                } else {
                    onLoginFailed(response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), getString(R.string.network_fail), Toast
                            .LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.conversation_fail),
                            Toast
                                    .LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        backpress = (backpress + 1);

        if (backpress > 1) {
            moveTaskToBack(true);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.back_exit),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void onLoginSuccess() {
        System.out.println(mUser.toString());
        saveUser(mUser, this);
        if (!mUser.getUserType().equals("Admin")) {
            setComm();
        }

        _loginButton.setEnabled(true);
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        System.out.println("USUARIOL " + mUser.toString());
        System.out.println("tipo1: " + mUser.getUserType());
        startActivity(i);
    }

    public void setComm() {
        setUpPusher();
        setUpChannelUser(this);
        setChannelUserChat(this);
        sendGetChats();
        if (mUser.getUserType().equals("Refugee")) {
            setChannelUserService(this);
            sendGetMyServicesRefugee();
        }
        connectPusher();
    }

    public void sendGetChats() {
        mAPIService.getChats(mUser.getMail()).enqueue(new Callback<ArrayList<Chat>>() {
            @Override
            public void onResponse(Call<ArrayList<Chat>> call, Response<ArrayList<Chat>> response) {
                if (response.isSuccessful()) {
                    manageResultGetChats(true, response.body());
                } else {
                    manageResultGetChats(false, null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Chat>> call, Throwable t) {
                manageResultGetChats(false, null);
            }
        });
    }

    public void manageResultGetChats(boolean result, ArrayList<Chat> listChats) {
        if (result) {
            setUpChannelsChats(this, listChats);
            setAllChannelsNotificationsChats(this);
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.error), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void sendGetMyServicesRefugee() {
        mAPIService.obtenerMisServicios(mUser.getMail(), mUser.getUserType(), false).enqueue(
                new Callback<ArrayList<Service>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Service>> call,
                            Response<ArrayList<Service>> response) {
                        if (response.isSuccessful()) {
                            manageResultGetServices(true, response.body());
                        } else {
                            manageResultGetServices(false, null);
                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Service>> call, Throwable t) {
                        manageResultGetServices(false, null);
                    }
                });
    }

    public void manageResultGetServices(boolean result, ArrayList<Service> listServices) {
        if (result) {
            setUpChannelsServices(listServices);
            setAllChannelsNotificationsServices(this);
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.error), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void onLoginFailed(int code) {

        if (code == 403) {
            Toast.makeText(getBaseContext(), getString(R.string.banned), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.login_fail),
                    Toast.LENGTH_LONG).show();
        }
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getString(R.string.email_formato));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError(getString(R.string.short_password));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
