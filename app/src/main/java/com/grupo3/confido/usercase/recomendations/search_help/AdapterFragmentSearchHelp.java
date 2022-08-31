package com.grupo3.confido.usercase.recomendations.search_help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.grupo3.confido.R;

import java.util.ArrayList;

public class AdapterFragmentSearchHelp extends RecyclerView.Adapter<AdapterFragmentSearchHelp.ViewHolder> implements View.OnClickListener {

    //Variables para cargar los distintos elementos de la lista de información
    LayoutInflater inflater;
    ArrayList<Help> modelHelp;

    //Listener
    private View.OnClickListener listener;



    public AdapterFragmentSearchHelp(Context context, ArrayList<Help> listHelp) {
        this.inflater = LayoutInflater.from(context);
        this.modelHelp = listHelp;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_recomendations_search_help_list, parent, false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nomHelp = modelHelp.get(position).getNomHelp();
        int imgHelp = modelHelp.get(position).getImgHelp();

        holder.nomHelp.setText(nomHelp);
        holder.imgHelp.setImageResource(imgHelp);
    }



    @Override
    public void onClick(View view) {

        if(listener != null){
            listener.onClick(view);
        }

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return modelHelp.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        Button clickHere;
        TextView nomHelp;
        ShapeableImageView imgHelp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomHelp = itemView.findViewById(R.id.txtNomHelp);
            imgHelp = itemView.findViewById(R.id.img_help);
            //clickHere=itemView.findViewById(R.id.btnClickHere);
            //clickHere.setOnClickListener(View-> Toast.makeText(itemView.getContext(), "PRUEBA BOTON", Toast.LENGTH_SHORT).show());
        }
    }

}
