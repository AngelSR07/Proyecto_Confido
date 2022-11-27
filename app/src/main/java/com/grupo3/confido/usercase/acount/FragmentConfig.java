package com.grupo3.confido.usercase.acount;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo3.confido.R;
import com.grupo3.confido.model.User;

import java.util.Objects;

public class FragmentConfig extends Fragment {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText nickName;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context context;
    User u;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        // Button btnBack = view.findViewById(R.id.btnBackViewHelp);
        firstName= view.findViewById(R.id.modify_name_input);
        lastName= view.findViewById(R.id.modify_surname_input);
        email= view.findViewById(R.id.modify_email_input);
        nickName= view.findViewById(R.id.modify_nickname_input);
        Button btnSave= (Button)view.findViewById(R.id.modify_enter_btn);


        inicializarFirebase();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nom=snapshot.child("firstName").getValue().toString();
                    firstName.setText(""+nom);
                    String ape=snapshot.child("lastName").getValue().toString();
                    lastName.setText(""+ape);
                    String ema=snapshot.child("email").getValue().toString();
                    email.setText(""+ema);
                    String nick=snapshot.child("nickName").getValue().toString();
                    nickName.setText(""+nick);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSave.setOnClickListener(v -> modifyUser());

        return view;
    }




    private void inicializarFirebase(){

        FirebaseApp.initializeApp(context);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //  databaseReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference=firebaseDatabase.getReference("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
    }



    public void modifyUser() {


        User c = new User();
        c.setFirstName(firstName.getText().toString().trim());
        c.setLastName(lastName.getText().toString().trim());
        c.setEmail(email.getText().toString().trim());
        c.setNickName(nickName.getText().toString().trim());
        databaseReference.setValue(c);
        Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_LONG).show();
        //mostrar();


    }

}