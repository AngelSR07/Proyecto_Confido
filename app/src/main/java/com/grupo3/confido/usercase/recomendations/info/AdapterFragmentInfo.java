package com.grupo3.confido.usercase.recomendations.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.grupo3.confido.R;

import java.util.ArrayList;

public class AdapterFragmentInfo extends RecyclerView.Adapter<AdapterFragmentInfo.ViewHolder> implements View.OnClickListener {

    //Variables para cargar los distintos elementos de la lista de información
    LayoutInflater inflater;
    ArrayList<Info> modelInfo;

    //Listener
    private View.OnClickListener listener;



    public AdapterFragmentInfo(Context context, ArrayList<Info> listInfo) {
        this.inflater = LayoutInflater.from(context);
        this.modelInfo = listInfo;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_recomendations_info_list, parent, false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nomInfo = modelInfo.get(position).getNomInfo();
        int imgInfo = modelInfo.get(position).getImgInfo();

        holder.nomInfo.setText(nomInfo);
        holder.imgInfo.setImageResource(imgInfo);
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
        return modelInfo.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomInfo;
        ShapeableImageView imgInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomInfo = itemView.findViewById(R.id.txtNomInfo);
            imgInfo = itemView.findViewById(R.id.img_info);
        }
    }

}
