package com.grupo3.confido.usercase.service;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grupo3.confido.R;
import com.grupo3.confido.util.backgroundService.Service_Message;

public class Fragment_Service extends Fragment {

    //Variable mensaje servicio 2do plano
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICATION_ID = 0;
    private View view;

    //Botones
    Button startService;
    Button stopService;

    public Fragment_Service() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view == null){
            // Inflate the layout for this fragment
            view =  inflater.inflate(R.layout.fragment_service, container, false);
        } else {
            ((ViewGroup)view.getParent()).removeView(view);
        }

        startService = (Button) view.findViewById(R.id.btnStartService);
        stopService = (Button) view.findViewById(R.id.btnStopService);

        startService.setOnClickListener(View -> startConfidoService());
        stopService.setOnClickListener(View -> stopConfidoService());

        return view;
    }



    public void startConfidoService(){
        Intent onCondifoService = new Intent(getActivity(), Service_Message.class);
        getActivity().startService(onCondifoService);

        createNotificationChannel();
        createNotification();

        startService.setEnabled(false);
        stopService.setEnabled(true);

        startService.setBackgroundColor(Color.parseColor("#DFE0E4"));
        stopService.setBackgroundColor(Color.parseColor("#7972E6"));
    }


    public void stopConfidoService(){

        Intent offCondifoService = new Intent(getActivity(),Service_Message.class);
        getActivity().stopService(offCondifoService);

        closeNotification();

        startService.setEnabled(true);
        stopService.setEnabled(false);

        startService.setBackgroundColor(Color.parseColor("#7972E6"));
        stopService.setBackgroundColor(Color.parseColor("#DFE0E4"));
    }



    //Preparamos el canal por el cual la notificación va a ser transmitido
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//O = Hace referencia a la version "OREO" API 26
            CharSequence name = "Notificacion";

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name,
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    //Creamos una notificación para que el usuario sepa que la aplicación se está ejecutando en 2do plano
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Servicio de mensaje de ayuda");
        builder.setContentText("Servicio \"Confido\" activado.");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }


    //Metodo que permite cerrar la notificación
    private void closeNotification() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.cancel(NOTIFICATION_ID);
    }

}