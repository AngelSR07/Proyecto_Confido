package com.grupo3.confido.usercase.recomendations.info.dialog_welcome_info;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grupo3.confido.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentInfoWelcome extends Fragment {

    StringBuffer stringBuffer;
    SpannableStringBuilder spannable;
    Matcher matcher;


    public FragmentInfoWelcome() {
    }


    public void viewDialogWelcome(Context context, LayoutInflater inflater){

        AlertDialog.Builder builderView = new AlertDialog.Builder(context);

        View view = inflater.inflate(R.layout.fragment_recomendations_info_welcome, null);

        TextView txtDescWelcome = view.findViewById(R.id.txtDescWelcome);
        ImageButton imgCloseView = view.findViewById(R.id.imgCloseBoxWelcome);
        Button btnColseView = view.findViewById(R.id.btnCloseBox);

        builderView.setView(view);

        AlertDialog dialogWelcome = builderView.create();

        //Aplicamos un diseño transparente y margin 40dp a la caja de dialogo
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 40);

        dialogWelcome.getWindow().setBackgroundDrawable(inset);

        dialogWelcome.show();

        String text = "A continuación, te presentamos {los 9 conceptos} que han sido trabajado por el {diario el Comercio} en conjunto con el apoyo de la Defensoría del Pueblo en su página web {\"Estamos hartas\"}";
        txtDescWelcome.setText(changeColorText(text, "\\{.*?\\}"));

        imgCloseView.setOnClickListener(View -> dialogWelcome.dismiss());
        btnColseView.setOnClickListener(View -> dialogWelcome.dismiss());

    }


    private SpannableStringBuilder changeColorText(String text, String regex){

        stringBuffer = new StringBuffer();
        spannable = new SpannableStringBuilder();

        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text);

        parseTextPatter();

        stringBuffer.setLength(0);
        matcher.appendTail(stringBuffer);
        spannable.append(stringBuffer.toString());

        return spannable;
    }

    private void parseTextPatter() {

        if(matcher.find()){
            stringBuffer.setLength(0); //Limpiamos

            String group = matcher.group();

            String spanText = group.substring(1,group.length() - 1);
            matcher.appendReplacement(stringBuffer,spanText);

            spannable.append(stringBuffer.toString());
            int start = spannable.length() - spanText.length();

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#7972E6")), start, spannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            parseTextPatter();
        }

    }


}