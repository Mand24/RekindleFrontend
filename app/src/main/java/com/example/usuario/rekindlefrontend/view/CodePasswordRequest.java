package com.example.usuario.rekindlefrontend.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.utils.CodeGenerator;
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
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + email));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password recovery code");
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    "Your password recovery code is: " + codeString);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                Intent i = new Intent(getApplicationContext(), RecuperarPassword.class);
                i.putExtra("email", email);
                i.putExtra("code", codeString);
                startActivity(i);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(CodePasswordRequest.this, "No email clients installed.",
                        Toast.LENGTH_SHORT).show();
            }
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
