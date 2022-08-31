package com.grupo3.confido.usercase.recomendations.guide;

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


public class FragmentGuide extends Fragment {


    int c = 0;

    public void viewDialogWelcome(Context context, LayoutInflater layoutInflater) {


        AlertDialog.Builder builderView = new AlertDialog.Builder(context);

        View view = layoutInflater.inflate(R.layout.fragment_recomendations_guide, null);

        TextView txtDescWelcome = view.findViewById(R.id.txtDesc);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        ImageButton imgCloseView = view.findViewById(R.id.imgCloseBoxWelcome);
        Button btnNext = view.findViewById(R.id.btnNext);

        builderView.setView(view);

        AlertDialog dialogWelcome = builderView.create();

        //Aplicamos un diseño transparente y margin 40dp a la caja de dialogo
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 40);

        dialogWelcome.getWindow().setBackgroundDrawable(inset);

        dialogWelcome.show();


        String textTitle = "Hola";
        String text = "¿Estás siendo acosada?\n" +
                " ¿Sientes que te observan o te persiguen?\n" +
                "¿Sientes temor al caminar por la calles?\n" +
                "\n" +
                "Tranquila, recuerda que nunca estas\n" +
                "sola, por ello, te tenemos algunas \n" +
                "recomendaciones para afrontar\n" +
                "dichas situaciones.";
        txtDescWelcome.setText((text));
        txtTitle.setText(textTitle);

        imgCloseView.setOnClickListener(View -> dialogWelcome.dismiss());
        btnNext.setOnClickListener(view1 -> dialog1(txtTitle, txtDescWelcome, btnNext));
        //btnColseView.setOnClickListener(View -> dialogWelcome.dismiss());
    }


    public void dialog1(TextView title, TextView desc, Button btn) {
        c++;

        String alterTitle = "";
        String alterText = "";

        switch (c) {
            case 1:
                alterTitle = "No te quedes callada";
                alterText = "Mediante está aplicación comunica\n" +
                        "tu sentir a tus contactos de confianza.\n" +
                        "\n" +
                        "No tienes que sacar el celular de tu\n" +
                        "bolliso o ponerlo a la vista de todo\n" +
                        "público.\n" +
                        "\n" +
                        "Solo basta con presionar dos veces\n" +
                        "el boton de “Subir Volumen”";
                break;
            case 2:
                alterTitle = "Busca ayuda inmediata";
                alterText = "Denuncia a tu agresor con alguna\n" +
                        "autoridad." +
                        "\n" +
                        "Tu puedes contribuir a reducir el \n" +
                        "porcentaje de acoso que existe contra\n" +
                        " la mujer en el país.\n" +
                        "\n" +
                        "En esta app, en la opción “Busca Ayuda”\n" +
                        "tienes algunos números de autoridades \n las cuales siempre se van a encontrar a\n" +
                        "tu disposición. ";
                break;
            case 3:
                alterTitle = "Comparte tu experiencia";
                alterText = "Tu experiencia puede ayudar a más\n" +
                        "mujeres a que rompan su silencio.\n" +
                        "\n" +
                        "En ese aspecto, el diario comercio\n" +
                        "cuenta con un espacio en internet \n" +
                        "llamado “Estamos hartas”, la cual,\n" +
                        "te permite denunciar publicamente\n" +
                        "tu caso. ";
                c = 0;
                btn.setVisibility(View.GONE);
                break;
        }

        title.setText(alterTitle);
        desc.setText((alterText));

    }
}