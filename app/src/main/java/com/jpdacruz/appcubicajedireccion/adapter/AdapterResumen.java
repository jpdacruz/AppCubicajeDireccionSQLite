package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.SiloSuma;

import java.util.ArrayList;

public class AdapterResumen extends RecyclerView.Adapter<AdapterResumen.ViewHolder> {

    ArrayList<SiloSuma>siloSumas;

    public AdapterResumen(ArrayList<SiloSuma> siloSumas) {
        this.siloSumas = siloSumas;
    }

    @NonNull
    @Override
    public AdapterResumen.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_resumen,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResumen.ViewHolder holder, int position) {

        holder.granos.setText(siloSumas.get(position).getTipoGrano());
        holder.totales.setText(String.valueOf(siloSumas.get(position).getCubicaje()));
    }

    @Override
    public int getItemCount() {
        return siloSumas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView granos, totales;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            granos = itemView.findViewById(R.id.textViewTGResumen);
            totales = itemView.findViewById(R.id.textViewTotalResumen);
        }
    }
}
