package com.example.usuario.rekindlefrontend.view.menu.login;

import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getChannel;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getPusher;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpPusher;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.servicios.editar.EditarServicio;
import com.example.usuario.rekindlefrontend.view.usuarios.registro.RegistroUsuario;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.io.IOException;
import java.util.Set;

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
    private Usuario usuario;

    private void bind(){
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
        comprobar_login();
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

        _recuperarPasswordLink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CodePasswordRequest.class);
                startActivity(i);
            }
        });
    }

    public void comprobar_login(){
        //TODO sharepreference consistencyutils?
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        if (json.isEmpty()){
            return;
        }
        else {
            usuario = gson.fromJson(json, Usuario.class);
            Intent i = new Intent(this, MenuPrincipal.class);
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
        progressDialog.setMessage(getString (R.string.autentificacion));
        progressDialog.show();

        //String email = _emailText.getText().toString();
        //String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginSuccess();
                        // onLoginFailed();

                        sendLogin();

                        progressDialog.dismiss();
                    }
                }, 1500);
    }

    public void sendLogin(){
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        mAPIService.login(email, password).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                /*Set<String> headers = response.headers().names();
                for(String header : headers) {
                    System.out.println("cabecera: "+header);
                }*/

//                System.out.println(response.code());

                if (response.isSuccessful()) {
                    /*String header1 = response.headers().get("Tipo");
                    int i = Integer.parseInt(header1);*/
                    usuario = response.body();
//                    usuario.setTipo(i);
                    System.out.println("tipo: "+usuario.getTipo());
                    onLoginSuccess();
                }
                else {
                    onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), getString (R.string.network_fail), Toast
                            .LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(getApplicationContext(), getString (R.string.conversation_fail), Toast
                            .LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        backpress = (backpress + 1);

        if (backpress>1) {
            moveTaskToBack(true);
        }
        else{
            Toast.makeText(getApplicationContext(), getString (R.string.back_exit), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void onLoginSuccess() {

        /*SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor = datos.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        miEditor.putString("usuario", json);
        miEditor.apply();*/

        saveUser(usuario,this);
        setUpPusher();
        runPusher();
        _loginButton.setEnabled(true);
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        System.out.println("USUARIOL "+usuario.toString());
        System.out.println("tipo1: "+usuario.getTipo());
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
                        /*NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager)
                                getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.ic_launcher;
                        *//*Intent i=new Intent(MainActivity.this, MensajeActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);*//*

                        mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(icono)
                                .setContentTitle("Titulo")
                                .setContentText("Hola que tal?")
                                .setVibrate(new long[] {100, 250, 100, 500})
                                .setAutoCancel(true);

                        mNotifyMgr.notify(1, mBuilder.build());*/

                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);

                        // Create a PendingIntent; we're only using one PendingIntent (ID = 0):
                        final int pendingIntentId = 0;
                        PendingIntent pendingIntent =
                                PendingIntent.getActivity(getApplicationContext(), pendingIntentId, intent,
                                        PendingIntent.FLAG_ONE_SHOT);

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
        Toast.makeText(getBaseContext(), getString (R.string.login_fail), Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    //TODO pasar estas comprovaciones a AbstractFormatChecker
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getString (R.string.email_formato));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError(getString (R.string.contraseña_corta));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
