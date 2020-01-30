package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Celda;

import java.util.ArrayList;

public class AdapterCeldas extends RecyclerView.Adapter<AdapterCeldas.ViewHolder>
implements View.OnClickListener {

    ArrayList<Celda> celdas;
    private View.OnClickListener listener;

    public AdapterCeldas(ArrayList<Celda> celdas) {
        this.celdas = celdas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_listaceldas,null,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(String.format("ID Celda: %s", celdas.get(position).getId()));
        holder.tipoGrano.setText(String.format("Grano: %s", celdas.get(position).getTipoGrano()));
        holder.phGrano.setText(String.format("PH: %s", String.valueOf(celdas.get(position).getPhGrano())));
        holder.largo.setText(String.format("Largo: %s m", String.valueOf(celdas.get(position).getLargoCelda())));
        holder.ancho.setText(String.format("Ancho: %s m", String.valueOf(celdas.get(position).getAnchoCelda())));
        holder.altuGrano.setText(String.format("Alt Grano: %s m", String.valueOf(celdas.get(position).getAltoGrano())));
        holder.tipo.setText(String.format("Tipo: %s", celdas.get(position).getTipoCelda()));
        holder.cono.setText(String.format("Cono: %s m", String.valueOf(celdas.get(position).getCono())));
        holder.copete.setText(String.format("Copete: %s m", String.valueOf(celdas.get(position).getCopete())));
        holder.tons.setText(String.valueOf(celdas.get(position).getTotaltons()));
    }

    @Override
    public int getItemCount() {

        return celdas.size();
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

        TextView id,tipoGrano,phGrano, largo, ancho, altuGrano,tipo, cono, copete, tons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewIdLSCelda);
            tipoGrano = itemView.findViewById(R.id.textViewTGLSCelda);
            phGrano = itemView.findViewById(R.id.textViewPHLSCelda);
            largo = itemView.findViewById(R.id.textViewTamLSCelda);
            ancho = itemView.findViewById(R.id.textViewAncLSCelda);
            altuGrano = itemView.findViewById(R.id.textViewAlGrLSCelda);
            tipo = itemView.findViewById(R.id.textViewTipoLSCelda);
            cono = itemView.findViewById(R.id.textViewConoLSCelda);
            copete = itemView.findViewById(R.id.textViewCopLSCelda);
            tons = itemView.findViewById(R.id.textViewTonSLCelda);
        }
    }
}
