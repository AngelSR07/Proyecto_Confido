package com.grupo3.confido.usercase.recomendations.info.dialog_info_container;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grupo3.confido.R;


public class FragmentInfoDialogBox extends Fragment {

    public FragmentInfoDialogBox() {
    }


    public void viewDialogInfo(Context context, LayoutInflater inflater, String tittle, String descrip){

        AlertDialog.Builder builderView = new AlertDialog.Builder(context);

        View view = inflater.inflate(R.layout.fragment_recomendations_info_dialog_box, null);

        TextView txtTittleInfo = view.findViewById(R.id.txtTitleInfo);
        TextView txtDescInfo = view.findViewById(R.id.txtDescInfo);
        ImageButton imgCloseView = view.findViewById(R.id.imgCloseBoxInfo);

        txtTittleInfo.setText(tittle);
        txtDescInfo.setText(descrip);

        builderView.setView(view);

        AlertDialog dialogInfo = builderView.create();

        //Aplicamos un diseño transparente y margin 40dp a la caja de dialogo
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 40);

        dialogInfo.getWindow().setBackgroundDrawable(inset);

        dialogInfo.show();

        imgCloseView.setOnClickListener(View -> dialogInfo.dismiss());

    }


}