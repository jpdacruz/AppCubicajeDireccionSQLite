package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Silo;


import java.util.ArrayList;

public class AdapterSilos extends RecyclerView.Adapter<AdapterSilos.ViewHolder>
                        implements View.OnClickListener{

    ArrayList<Silo> silos;
    private View.OnClickListener listener;

    public AdapterSilos(ArrayList<Silo> silos) {
        this.silos = silos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_listasilos,null,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(String.format("ID Silo: %s", silos.get(position).getId()));
        holder.tipoGrano.setText(silos.get(position).getTipoGrano());
        holder.phGrano.setText(String.format("PH: %s", String.valueOf(silos.get(position).getPhGrano())));
        holder.diametro.setText(String.format("Diametro: %s m", String.valueOf(silos.get(position).getDiametro())));
        holder.altuGrano.setText(String.format("Alt Grano: %s m", String.valueOf(silos.get(position).getAltoGrano())));
        holder.cono.setText(String.format("Cono: %s m", String.valueOf(silos.get(position).getCono())));
        holder.copete.setText(String.format("Copete: %s m", String.valueOf(silos.get(position).getCopete())));
        holder.tons.setText(String.valueOf(silos.get(position).getTotaltons()));
    }

    @Override
    public int getItemCount() {
        return silos.size();
    }


    public void setOnClickListener (View.OnClickListener listener) {
        this.listener = listener;

    }

    @Override
    public void onClick(View v) {
        if (listener != null){

            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id,tipoGrano,phGrano, diametro, altuGrano, cono, copete, tons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewIdLS);
            tipoGrano = itemView.findViewById(R.id.textViewTGLS);
            phGrano = itemView.findViewById(R.id.textViewPHLS);
            diametro = itemView.findViewById(R.id.textViewDiaLS);
            altuGrano = itemView.findViewById(R.id.textViewAlGrLS);
            cono = itemView.findViewById(R.id.textViewConoLS);
            copete = itemView.findViewById(R.id.textViewCopLS);
            tons = itemView.findViewById(R.id.textViewTonSL);
        }
    }
}
