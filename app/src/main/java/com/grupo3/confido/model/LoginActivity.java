package com.grupo3.confido.model;

/*======== Imports ========*/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.grupo3.confido.R;

@SuppressWarnings("Convert2Lambda")
public class LoginActivity extends AppCompatActivity {

    /*======== Variables ========*/
    private FirebaseAuth mAuth;
    Button btnLogin;
    Button btnRegister;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*======== Lock Horizontal mode ========*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*======== Get Id's ========*/
        btnLogin = findViewById(R.id.login_enter_btn);
        btnRegister = findViewById(R.id.login_register_btn);

        //*======== Firebase Authentication ========*/
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        /*======== Set Authenticate onClick evt ========*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        /*======== Set Register onClick evt ========*/
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { registerUser(); }
        });
    }

    /*======== Activity Authenticate Function ========*/
    private void authenticateUser() {

        /*======== Get Id's ========*/
        EditText etLoginEmail = findViewById(R.id.login_email_input);
        EditText etLoginPassword = findViewById(R.id.login_password_input);

        /*======== Convert to String the Id's ========*/
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Porfavor, completa los datos.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainActivity();
                        } else {
                            Toast.makeText(LoginActivity.this, "Datos Incorrectos!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*======== Activity Show Menu Function ========*/
    private void showMainActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    /*======== Activity Register Function ========*/
    private void registerUser() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}