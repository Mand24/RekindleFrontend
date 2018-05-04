package com.example.usuario.rekindlefrontend.view.menu.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import com.example.usuario.rekindlefrontend.view.usuarios.registro.RegistroUsuario;
import com.google.gson.Gson;

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
                Intent i = new Intent(getApplicationContext(), RegistroUsuario.class);
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
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = datos.getString("usuario", "");
        if (json.isEmpty()){
            return;
        }
        else {
            usuario = gson.fromJson(json, Usuario.class);
            Intent i = new Intent(this, MenuPrincipal.class);
            i.putExtra("tipo", usuario.getTipo());
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
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //String email = _emailText.getText().toString();
        //String password = _passwordText.getText().toString();


        // TODO: Llamar API
        /*try{
            //result = new AsyncTaskCall().execute(email, password).get();
            //result = true;
            //if (result) onLoginSuccess();

        }catch (Exception e){
            e.printStackTrace();
        }*/

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
                Set<String> headers = response.headers().names();
                for(String header : headers) {
                    System.out.println("cabecera: "+header);
                }

                System.out.println(response.code());

                if (response.isSuccessful()) {
                    String header1 = response.headers().get("Tipo");
                    int i = Integer.parseInt(header1);
                    usuario = response.body();
                    usuario.setTipo(i);
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
                    Toast.makeText(getApplicationContext(), "this is an actual network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(getApplicationContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }

    /*private class AsyncTaskCall extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            //showProgress(true);
        }

        protected Boolean doInBackground(String... params) {

            String url = getResources().getString(R.string.url_server);
            System.out.println("url servidor: " + url);
            boolean result = false;
            try {
                result = ComunicacionUsuarios.iniciarSesion(url, params[0], params[1]);
                //result = ComunicacionUsuarios.test2(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;
        }
    }*/

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        backpress = (backpress + 1);

        if (backpress>1) {
            moveTaskToBack(true);
        }
        else{
            Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLoginSuccess() {
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor = datos.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        miEditor.putString("usuario", json);
        miEditor.apply();
        _loginButton.setEnabled(true);
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        i.putExtra("tipo", usuario.getTipo());
        System.out.println("tipo1: "+usuario.getTipo());
        startActivity(i);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("Password has to be at least 4 characters long");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
