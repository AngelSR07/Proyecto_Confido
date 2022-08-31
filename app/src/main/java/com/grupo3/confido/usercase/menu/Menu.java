package com.grupo3.confido.usercase.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.grupo3.confido.R;
import com.grupo3.confido.usercase.acount.FragmentConfig;
import com.grupo3.confido.usercase.home.FragmentHome;
import com.grupo3.confido.usercase.list_contact.FragmentListContact;
import com.grupo3.confido.usercase.recomendations.FragmentRecomendations;
import com.grupo3.confido.usercase.service.Fragment_Service;
import com.grupo3.confido.util.backgroundService.Service_Message;


public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables para diseñar la barra menu
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navView;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;


    //Variables para cargar el fragmento principal
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean didFragmentBStartATask;



    //Variables para el diseño del Item seleccionado
    private MenuItem selectedFeature;
    private MenuItem selectedProject;




    //Variable servicio
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICATION_ID = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*
         * ===============================================================================
         * ============================= Diseño Barra Menu ===============================
         * ===============================================================================
         * */

        toolbar = findViewById(R.id.nav_toolbar);       //Hacemos referencia al toolbar del layout "nav_menu_bar
        drawerLayout = findViewById(R.id.menu_layout);  //Referenciamos el "activity_main.xml"

        //Personalizamos la cabecera del menú con los id del layout "mobile_navigation.xml" em la carpeta "navigation"
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_contact_list, R.id.nav_configuration, R.id.nav_recomendations)
                .setOpenableLayout(drawerLayout)
                .build();

        setSupportActionBar(toolbar); //Establecemos quien será la barra de acción para abrir nuestro menú


        //Hacemos referencia al controlador donde mostraremos los fragments, el cual, se encuentra en el layout "nav_menu_content"
        navController = Navigation.findNavController(Menu.this, R.id.nav_fragment_content_main);

        //Aplicamos los cambios del diseño del menú
        NavigationUI.setupActionBarWithNavController(Menu.this, navController, appBarConfiguration);

        /*
         * ===============================================================================
         * ===============================================================================
         * */






        /*
         * ===============================================================================
         * ============================ Control navegación ===============================
         * ===============================================================================
         * */

        navView = findViewById(R.id.nav_view_container);

        //Establecemos el evento onclick al "NavigationView" que se encuentra en en layout "activity_main.xml"
        navView.setNavigationItemSelectedListener(Menu.this);

        //Cargamos el fragmento principal - "fragmentHome"
        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.nav_fragment_content_main, new FragmentHome())
                    .commit();
        }

        /*
         * ===============================================================================
         * ===============================================================================
         * */






        /*
         * ===============================================================================
         * ================================ Cerrar Menu ==================================
         * ===============================================================================
         * */

        View header = navView.getHeaderView(0);
        TextView txtHeader = header.findViewById(R.id.txtMenuHeader);
        txtHeader.setOnClickListener(view -> drawerLayout.closeDrawer(GravityCompat.START));

        /*
         * ===============================================================================
         * ===============================================================================
         * */
    }



    /*
     * Permite cargar los fragmentos en el contenedor principal que está en la actividad "nav_content_main"
     * Además, hace que el layout del menú sea interactivo
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



    //Programamos el evento onClick de los elementos de la barra de menú
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Permite cerrar el menú cada vez que se selecciona un elemento de este

        drawerLayout.closeDrawer(GravityCompat.START);

        fragmentTransaction = fragmentManager.beginTransaction();

        switch (item.getItemId()){
            case R.id.nav_home:
                    fragmentTransaction.replace(R.id.nav_fragment_content_main, new FragmentHome())
                            .addToBackStack(null);
                    selectedItem(item);
                break;

            case R.id.nav_contact_list:
                    fragmentTransaction.replace(R.id.nav_fragment_content_main, new FragmentListContact())
                            .addToBackStack(null);
                    selectedItem(item);
                break;

            case R.id.nav_configuration:
                    fragmentTransaction.replace(R.id.nav_fragment_content_main, new FragmentConfig())
                            .addToBackStack(null);
                    selectedItem(item);
                break;

            case R.id.nav_recomendations:
                    fragmentTransaction.replace(R.id.nav_fragment_content_main, new FragmentRecomendations())
                            .addToBackStack(null);
                    selectedItem(item);
                break;

            case R.id.nav_service:
                fragmentTransaction.replace(R.id.nav_fragment_content_main, new Fragment_Service())
                        .addToBackStack(null);
                selectedItem(item);
                break;

        }

        fragmentTransaction.commit();

        return false;

    }



    private void selectedItem(@NonNull MenuItem item){
        if(selectedFeature != null){
            selectedFeature.setChecked(false);
        }
        selectedFeature = item;
        selectedFeature.setChecked(true);
    }

}