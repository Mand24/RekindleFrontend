package com.example.usuario.rekindlefrontend.view.menu.login;

import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getChannel;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getPusher;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpPusher;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MainMenu;
import com.example.usuario.rekindlefrontend.view.usuarios.registro.RegistroUsuario;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private int backpress = 0;
    EditText _emailText;
    EditText _passwordText;
    TextView _signupLink;
    TextView _recuperarPasswordLink;
    Button _loginButton;

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
        setContentView(R.layout.activity_pantalla_inicio);

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
                Intent i = new Intent(getApplicationContext(), RegistroUsuario.class); //HERE
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
        //TODO sharepreference consistencyutils?
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("mUser", "");
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
            onLoginFailed();
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

                if (response.isSuccessful()) {
                    mUser = response.body();
                    System.out.println("tipo: " + mUser.getUserType());
                    onLoginSuccess();
                } else {
                    onLoginFailed();
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

        saveUser(mUser, this);
        setUpPusher();
        runPusher();
        _loginButton.setEnabled(true);
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        System.out.println("USUARIOL " + mUser.toString());
        System.out.println("tipo1: " + mUser.getUserType());
        startActivity(i);
    }

    public void runPusher() {
        Pusher pusher = getPusher();
        Channel channel = getChannel();

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr = (NotificationManager)
                                getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.ic_launcher;

                        // Instantiate the builder and set notification elements:
                        Notification.Builder builder = new Notification.Builder
                                (getApplicationContext());
                        builder.setContentTitle("hola");
                        builder.setContentText("que tal?");
                        builder.setSmallIcon(R.mipmap.ic_launcher);

                        // Build the notification:
                        Notification notification = builder.build();

                        // Get the notification manager:
                        NotificationManager notificationManager =
                                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        // Publish the notification:
                        final int notificationId = 0;
                        notificationManager.notify(notificationId, notification);
                    }

                });
            }
        });

        pusher.connect();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), getString(R.string.login_fail), Toast.LENGTH_LONG).show();

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
            _passwordText.setError(getString(R.string.contraseña_corta));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
