package com.grupo3.confido.model;

/*======== Imports ========*/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo3.confido.R;
import com.grupo3.confido.usercase.menu.Menu;

@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda", "ConstantConditions"})
public class RegisterActivity extends AppCompatActivity {

    /*======== Variables ========*/
    private ImageButton btnBackHome;
    private FirebaseAuth mAuth;
    Button btnRegister;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*======== Lock Horizontal mode ========*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*======== Get Id's ========*/
        btnRegister = findViewById(R.id.register_enter_btn);
        btnBackHome = findViewById(R.id.register_back_btn);

        //*======== Firebase Authentication ========*/
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        /*======== Set Register onClick evt ========*/
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        /*======== Set Back Button onClick evt ========*/
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backHomeActivity();
            }
        });
    }

    /*======== Activity Register Function ========*/
    private void registerUser() {

        /*======== Get Id's ========*/
        EditText etEmail = findViewById(R.id.register_email_input);
        EditText etNickname = findViewById(R.id.register_nickname_input);
        EditText etName = findViewById(R.id.register_name_input);
        EditText etSurname = findViewById(R.id.register_surname_input);
        EditText etPassword = findViewById(R.id.register_password_input);

        /*======== Convert to String the Id's ========*/
        String email = etEmail.getText().toString();
        String nickName = etNickname.getText().toString();
        String firstName = etName.getText().toString();
        String lastName = etSurname.getText().toString();
        String password = etPassword.getText().toString();


        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()|| nickName.isEmpty()) {
            Toast.makeText(this, "Por favor, llene todos los campos.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firstName, lastName, email,nickName);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showMenuActivity();
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterActivity.this, "La autentificación falló",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    /*======== Activity Menu Function ========*/
    private void showMenuActivity() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }

    /*======== Activity Back Main Title Function ========*/
    private void backHomeActivity() {
        Intent intent_back_welcome = new Intent(this, LoginActivity.class);
        startActivity(intent_back_welcome);
        finish();
    }
}