package com.grupo3.confido.model;

/*======== Imports ========*/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo3.confido.R;
import com.grupo3.confido.usercase.menu.Menu;

public class WelcomeActivity extends AppCompatActivity {

    /*======== Variables ========*/
    Button btnWelcome;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /*======== Lock Horizontal mode ========*/
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*======== Get Id's ========*/
        btnWelcome= findViewById(R.id.welcome_login_btn);

        /*======== Set Home onClick evt ========*/
        btnWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeContinue();
            }
        });
    }

    /*======== Activity Home Function ========*/
    private  void homeContinue(){
        Intent intent_back_welcome = new Intent(this,
                Menu.class);
        startActivity(intent_back_welcome);
        finish();
    }
}