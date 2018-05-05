package com.example.usuario.rekindlefrontend.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.CodeGenerator;
import com.example.usuario.rekindlefrontend.utils.SendMailTask;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;

public class CodePasswordRequest extends AppCompatActivity {

    private EditText _email;
    private Button _codeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_password_request);

        _email = (EditText) findViewById(R.id.emailCodeRequest);

        _codeRequest = (Button) findViewById(R.id.request_password_change);

        _codeRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail(){
        String email =  _email.getText().toString();
        if(!email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher
                (email).matches()) {
            CodeGenerator code = new CodeGenerator(5);
            String codeString = code.getCode();

            /*Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", email,
                    "null"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password recovery code");
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    "Your password recovery code is: " + codeString);
            emailIntent.setType("message/rfc822");*/

            Log.i("SendMailActivity", "Send Button Clicked.");

            new SendMailTask(CodePasswordRequest.this).execute("apprekindle@gmail.com",
                    "12rekindle34", email, "Password recovery code", "Your password recovery code"
                            + " is: " + codeString);

            Intent i = new Intent(getApplicationContext(), RecuperarPassword.class);
            i.putExtra("email", email);
            i.putExtra("code", codeString);
            startActivity(i);

        }
        else{
            _email.setError("Enter a valid email address");
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
        startActivity(i);
    }
}
