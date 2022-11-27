package com.grupo3.confido.usercase.recomendations.info;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grupo3.confido.R;
import com.grupo3.confido.usercase.recomendations.FragmentRecomendations;

public class FragmentInformation extends Fragment {

    //Atributo
    Activity Act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Act = getActivity();

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_information, container, false);

        Button btntypeInfo = root.findViewById(R.id.btnTypeInfo);
        btntypeInfo.setOnClickListener(view -> goToNewFragment());

        Button btnCases = root.findViewById(R.id.btnCasesOfViolence);
        btnCases.setOnClickListener(view -> goToNewFragment2());

        Button btnBack = root.findViewById(R.id.btnBackViewInfo);
        btnBack.setOnClickListener(view -> goToBack() );

        return root;
    }

    public void goToNewFragment() {
        //requireActivity() --> Metodo que nos permite hacer referencia a la actividad actual y se asegura que dicha referencia no sea nulo.
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        //                  replace(ID del fragmento a reemplazar (Actual), Nombre de la clase del fragmento a mostrar, null)
        fragmentTransaction.replace(R.id.fragment_information, FragmentInfo.class, null);

        fragmentTransaction.commit();
    }

    public void goToNewFragment2() {
        goUrl("https://www.mimp.gob.pe/omep/estadisticas-atencion-a-la-violencia.php");
    }

    public void goUrl(String url){
        Uri link= Uri.parse(url);
        Intent i=new Intent(Intent.ACTION_VIEW,link);
        Act.startActivity(i);
    }


    public void goToBack() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.replace(R.id.fragment_information, FragmentRecomendations.class, null);

        fragmentTransaction.remove(FragmentInformation.this).commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}