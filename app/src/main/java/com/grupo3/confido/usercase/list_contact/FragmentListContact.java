package com.grupo3.confido.usercase.list_contact;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo3.confido.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FragmentListContact extends Fragment implements View.OnClickListener{
    private final List<Contacto> contactoList= new ArrayList<>();
    ArrayAdapter<Contacto> arrayAdapterContacto;

    EditText nomC, numC;
    ListView list_contactos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FragmentListContact fragmentListContact;
    Contacto contactoSelected;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentListContact = new FragmentListContact();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);
        // Button btnBack = view.findViewById(R.id.btnBackViewHelp);
        nomC= view.findViewById(R.id.txtNombreContacto);
        numC= view.findViewById(R.id.txtNumeroContacto);
        Button btnAdd = (Button)view.findViewById(R.id.icon_add);
        Button btnSave= (Button)view.findViewById(R.id.icon_save);
        Button btnDelete = (Button)view.findViewById(R.id.icon_delete);
        //Button btnAdd = view.findViewById(R.id.btnAdd);
        list_contactos=view.findViewById(R.id.datosContactos);
        inicializarFirebase();
        btnAdd.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        listarDatos();

        list_contactos.setOnItemClickListener((parent, view1, position, id) -> {
            contactoSelected=(Contacto) parent.getItemAtPosition(position);
            nomC.setText(contactoSelected.getNombre());
            numC.setText(contactoSelected.getNumber());
        });

        return view;
    }


    private void listarDatos() {
        databaseReference.child("Contacto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactoList.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Contacto c=objSnapshot.getValue(Contacto.class);
                    contactoList.add(c);
                    arrayAdapterContacto= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, contactoList);
                    list_contactos.setAdapter(arrayAdapterContacto);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase(){

        FirebaseApp.initializeApp(context);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //  databaseReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference=firebaseDatabase.getReference("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
    }



    public void limpiarCajas(){
        nomC.setText("");
        numC.setText("");
    }



    public void onClick(View v) {

        String nombre = nomC.getText().toString();
        String numero = numC.getText().toString();
        //switch
        switch (v.getId()) {

            case R.id.icon_add: {
                if (nombre.equals("") || numero.equals("")) {
                    Toast.makeText(getContext(), "Insertar datos", Toast.LENGTH_LONG).show();
                    // validacion();

                } else {
                    Contacto c = new Contacto();
                    c.setId(UUID.randomUUID().toString());
                    c.setNombre(nombre);c.setNumber(numero);
                    databaseReference.child("Contacto").child(c.getId()).setValue(c);
                    Toast.makeText(getContext(), "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();

                }
                break;
            }
            case R.id.icon_save: {
                Contacto c = new Contacto();
                c.setId(contactoSelected.getId());
                c.setNombre(nomC.getText().toString().trim());
                c.setNumber(numC.getText().toString().trim());
                databaseReference.child("Contacto").child(c.getId()).setValue(c);
                Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete: {
                Contacto c = new Contacto();
                c.setId(contactoSelected.getId());
                databaseReference.child("Contacto").child(c.getId()).removeValue();
                Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
        }

    }

}