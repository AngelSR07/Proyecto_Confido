package com.grupo3.confido.usercase.recomendations.search_help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.confido.R;
import com.grupo3.confido.usercase.recomendations.FragmentChange;
import com.grupo3.confido.usercase.recomendations.FragmentRecomendations;
import com.grupo3.confido.usercase.recomendations.FragmentViewDate;
import com.grupo3.confido.usercase.recomendations.search_help.dialog_help_container.FragmentHelpDialogBox;

import java.util.ArrayList;


public class FragmentSearchHelp extends Fragment implements FragmentViewDate, FragmentChange {

    AdapterFragmentSearchHelp adapterFragmentSearchHelp;
    RecyclerView recyclerViewHelp;
    GridLayoutManager gridLayoutManager;
    ArrayList<Help> listHelp;
    ArrayList<String> listaDesc;

    FragmentHelpDialogBox fragInfoDialogHelp;
    int idItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragInfoDialogHelp = new FragmentHelpDialogBox();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recomendations_search_help, container, false);

        recyclerViewHelp = view.findViewById(R.id.recyclerView_help);

        listHelp = new ArrayList<>();
        listaDesc = new ArrayList<>();

        //Cargar lista
        loadList();

        //Mostrar datos
        viewList();

        Button btnBack = view.findViewById(R.id.btnBackViewHelp);

        btnBack.setOnClickListener(View -> goToNewFragment());

        return view;
    }



    @Override
    public void goToNewFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.replace(R.id.fragment_recomend_search_help, FragmentRecomendations.class, null);

        fragmentTransaction.remove(FragmentSearchHelp.this).commit();
    }


    @Override
    public void loadList(){

        loadDesc();

        listHelp.add(new Help("Los CEM", R.drawable.help_cem,listaDesc.get(0),1));
        listHelp.add(new Help("Línea 100", R.drawable.help_linea100,listaDesc.get(1),2));
        listHelp.add(new Help("Chat 100", R.drawable.help_chat100,listaDesc.get(2),3));
        listHelp.add(new Help("Línea gratuita en fiscalía", R.drawable.help_fiscalia,listaDesc.get(3),4));
        listHelp.add(new Help("Comisarías", R.drawable.help_comisaria,listaDesc.get(4),5));
        listHelp.add(new Help("Flora Tristán", R.drawable.help_flora_tristan,listaDesc.get(5),6));
    }

    private void loadDesc(){
        //Los CEM
        listaDesc.add("Los Centros de Emergencia Mujer son servicios públicos especializados y gratuitos para víctimas de violencia contra la mujer e integrantes del grupo familiar.\n" +
                "\n" +
                "Puedes consultar los CEM más cercanos a tu ubicación.");


        //Línea 100
        listaDesc.add("Es un servicio telefónico gratuito de información, orientación, consejería y soporte emocional que te ayudará si has sido afectada o involucrada en hechos de violencia familiar o sexual, o conoces algún caso de maltrato en tu entorno.\n" +
                "\n" +
                        "Puedes llamar a la línea 100");


        //Chat 100
        listaDesc.add("Servicio personalizado por internet y en tiempo real del Programa Nacional Contra la Violencia Familiar y Sexual. Trabaja con profesionales especializados en información y/u orientación psicológica para identificar situaciones de riesgo de violencia en las relaciones de enamoramiento y/o noviazgo.\n" +

                "\n" +
                        "Puedes ingresar al chat 100");


        //Fiscalia
        listaDesc.add("La Fiscalía de la Nación del Perú cuenta contigo para fortalecer la lucha contra la violencia contra la mujer en el Perú. Si actuamos todos juntos, tendremos un mejor país. Línea gratuita para informes y denuncias: 0-800-00-205.\n" +
                "\n" +
                "Puedes llamar a la fiscalía");


        //Comisarías
        listaDesc.add("Todas las comisarías de la Policía Nacional del Perú, independientemente de la especialidad, están obligadas a recibir, registrar y tramitar de inmediato las denuncias verbales o escritas de actos de violencia.\n" +
                "\n" +
                "Puedes consultar las comisarías más cercanas a tu ubicación.");


        //Flora Tristán
        listaDesc.add("El Centro de la Mujer Peruana Flora Tristán es una institución feminista que tiene como misión “combatir las causas estructurales que restringen la ciudadanía de las mujeres y/o afectan su ejercicio”.\n" +
        "\n" +
                "Puedes saber más de Flora Tristán");
    }



    @Override
    public void viewList(){
        recyclerViewHelp.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterFragmentSearchHelp = new AdapterFragmentSearchHelp(getContext(),listHelp);

        //GridLayoutManager(contexto, núm de columnas del RecyclerView, Horientación, Invertir layout (false | true))
        gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerViewHelp.setLayoutManager(gridLayoutManager);
        recyclerViewHelp.setAdapter(adapterFragmentSearchHelp);

        adapterFragmentSearchHelp.setOnClickListener(this::setOnClickListenerItem);
    }




    @Override
    public void setOnClickListenerItem(View view){
      //  view=getLayoutInflater().inflate(R.layout.fragment_recomendations_search_help_dialog_box,null);
        String nomHelp = listHelp.get(recyclerViewHelp.getChildAdapterPosition(view)).getNomHelp();
        String descripHelp = listHelp.get(recyclerViewHelp.getChildAdapterPosition(view)).getDescription();
        idItem=listHelp.get(recyclerViewHelp.getChildAdapterPosition(view)).getIdHelp();
        fragInfoDialogHelp.viewDialogHelp(getContext(), getLayoutInflater(), nomHelp, descripHelp,idItem,getActivity());
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}