package com.grupo3.confido.usercase.recomendations.search_help.dialog_help_container;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.grupo3.confido.R;


public class FragmentHelpDialogBox extends Fragment {
    Activity Act;
    public FragmentHelpDialogBox() {
    }


    public void viewDialogHelp(Context context, LayoutInflater inflater, String tittle, String descrip, int id, Activity Action){
        Act=Action;
        AlertDialog.Builder builderView = new AlertDialog.Builder(context);

        View view = inflater.inflate(R.layout.fragment_recomendations_search_help_dialog_box, null);

        TextView txtTittleHelp = view.findViewById(R.id.txtTitleHelp);
        TextView txtDescHelp = view.findViewById(R.id.txtDescHelp);
        ImageButton imgCloseView = view.findViewById(R.id.imgCloseBoxHelp);
        Button clickHere=(Button) view.findViewById(R.id.btnClickHere);

        txtTittleHelp.setText(tittle);
        txtDescHelp.setText(descrip);

        builderView.setView(view);

        AlertDialog dialogInfo = builderView.create();

        //Aplicamos un diseño transparente y margin 40dp a la caja de dialogo
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 40);

        dialogInfo.getWindow().setBackgroundDrawable(inset);

        dialogInfo.show();
        Log.e("Mensaje Boton","Boton"+String.valueOf(clickHere));
        imgCloseView.setOnClickListener(View -> dialogInfo.dismiss());
        clickHere.setOnClickListener(View-> btnAction(id));
    }
    private void btnAction(int id){
        switch (id){
            case 1:
                goUrl("https://www.gob.pe/institucion/aurora/informes-publicaciones/445097-centro-de-emergencia-mujer-regular");
                break;
            case 2:
               callHelp("100");
                break;
            case 3:
                goUrl("https://chat100.aurora.gob.pe");
                break;
            case 4:
                callHelp("0-800-00-205");
                break;
            case 5:
                goUrl("https://www.mininter.gob.pe/ubica-tu-comisaria");
                break;
            case 6:
                goUrl("http://www.flora.org.pe/web2/");
                break;
        }
    }
    public void goUrl(String url){
        Uri link= Uri.parse(url);
        Intent i=new Intent(Intent.ACTION_VIEW,link);
        Act.startActivity(i);
    }

    public void callHelp(String num){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",num,null));
        Act.startActivity(i);
    }



}