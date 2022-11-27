package com.grupo3.confido.util.backgroundService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo3.confido.usercase.list_contact.Contacto;
import com.grupo3.confido.util.locationService.Service_Location;
import com.grupo3.confido.util.sendMessage.Contact;
import com.grupo3.confido.util.sendMessage.RxJava;

import java.util.Objects;

public class Service_Message extends Service {

    //Atributos
    private MediaSessionCompat mediaSessionCompat;
    private int cont;
    private BroadcastReceiver myBroadcast;
    //private BroadcastReceiver locationBroadcast; --- GENERA ERROR DEL BUCLE
    private IntentFilter filter;
    private RxJava rxJava;
    private String url;


    //Atributos - Base de Datos
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;


//    private FusedLocationProviderClient fusedLocationClient;
//    private LocationRequest locationRequest;


    //Manejamos todos los componentes al inicio una vez cargada la clase
    @Override
    public void onCreate() {
        inicializarFirebase();

        Toast.makeText(this, "El servicio \"Confido\" ha sido creado", Toast.LENGTH_SHORT).show();

        mediaSessionCompat = new MediaSessionCompat(this, "Service_Message");

        mediaSessionCompat.setPlaybackState(new PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_PLAYING, 0, 0).build());

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(0);
//        locationRequest.setFastestInterval(0);

        rxJava = new RxJava();
        rxJava.addContext(this);

//        rxJava.addFused(fusedLocationClient);
//        rxJava.addLocation(locationRequest);


    }




    //Permite iniciar el servicio en segundo plano
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Servicio inciado " + startId, Toast.LENGTH_SHORT).show();

        filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);


        myBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Si la pantalla se encuentra suspendida o apagada
                if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                    VolumeProviderCompat myVolumenProvider = new VolumeProviderCompat(VolumeProviderCompat.VOLUME_CONTROL_RELATIVE,50,50) {
                        @Override
                        public void onAdjustVolume(int direction) {
                            //Si presiona el boton "Volumen +"
                            // <0 volume down
                            // >0 volume up
                            if(direction > 0){
                                cont++;

                                if(cont == 3){
                                    cont = 0;

                                    listContact();
                                    rxJava.startEvent("Hola");
                                    //getLocation(); --> GENERA EL BUCLE
                                }
                            }
                        }
                    };

                    mediaSessionCompat.setPlaybackToRemote(myVolumenProvider);
                    mediaSessionCompat.setActive(true);

                } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                    //Toast.makeText(Service_Message.this,"Se prendio la pantalla en 2do plano :D", Toast.LENGTH_LONG).show();
                    mediaSessionCompat.release();
                    cont = 0;
                }

            }
        };

        registerReceiver(myBroadcast,filter);

        return START_STICKY;//Bandera que indica que el servicio se está ejecuntado (1).

    }
/*    ===================================== GENERA EL ERROR DEL BUCLE ====================
    private void getLocation() {

        Intent i =new Intent(getBaseContext(),Service_Location.class);
        startService(i);

        if(locationBroadcast == null){
            locationBroadcast = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                     url = intent.getExtras().get("userurl")+"";
                    starService(url);
                }
            };
        }
        registerReceiver(locationBroadcast,new IntentFilter("location_update"));
    }

    public void starService(String urlUser){
        rxJava.startEvent(urlUser);
    }

     ===================================== GENERA EL ERROR DEL BUCLE ====================*/


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this.getBaseContext());
        firebaseDatabase= FirebaseDatabase.getInstance();
        //  databaseReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference=firebaseDatabase.getReference("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
    }


    //Metodo para añadir los contactos a notificar
    private void listContact(){

        databaseReference.child("Contacto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Contacto c =objSnapshot.getValue(Contacto.class);
                    rxJava.addContacts(c);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }




    //Permite detener el servicio en 2do plano
    @Override
    public void onDestroy() {

        Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();

        //rxJava.destroyDisposable();
        rxJava = null;//Limpiamos la lista de contactos

        unregisterReceiver(myBroadcast);
        //unregisterReceiver(locationBroadcast); --- GENERA ERROR
        mediaSessionCompat.release();

        super.onDestroy();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
