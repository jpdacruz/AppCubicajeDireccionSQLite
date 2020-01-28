package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.DiferenciaGrano;

import java.util.ArrayList;

public class AdapterDiferencia extends RecyclerView.Adapter<AdapterDiferencia.ViewHolder> implements View.OnClickListener {


    ArrayList<DiferenciaGrano> diferenciaGranos;
    private View.OnClickListener listener;

    public AdapterDiferencia(ArrayList<DiferenciaGrano> diferenciaGranos) {

        this.diferenciaGranos = diferenciaGranos;
    }


    @NonNull
    @Override
    public AdapterDiferencia.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_listadiferencia,null,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiferencia.ViewHolder holder, int position) {

        holder.grano.setText(diferenciaGranos.get(position).getGrano());
        holder.cubicaje.setText("Cubicaje: " + String.valueOf(diferenciaGranos.get(position).getCubicajeKg() + "kgs"));
        holder.afip.setText("AFIP: " + String.valueOf(diferenciaGranos.get(position).getAfip())+ "kgs");
        holder.diferencia.setText("Diferencia: " + String.valueOf(diferenciaGranos.get(position).getDiferencia()) + "kgs");
        holder.porcentaje.setText("Porcentaje: " + String.valueOf(diferenciaGranos.get(position).getPorcentaje()));
        holder.masomenos.setText(String.valueOf(diferenciaGranos.get(position).getMasOmenos()));
    }

    @Override
    public int getItemCount() {
        return diferenciaGranos.size();
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

        private TextView grano, cubicaje, afip, diferencia, porcentaje,masomenos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            grano = itemView.findViewById(R.id.textViewGranoRecDif);
            cubicaje = itemView.findViewById(R.id.textViewCubicajeRecDif);
            afip = itemView.findViewById(R.id.textViewAfipRecDif);
            diferencia = itemView.findViewById(R.id.textViewDifRecDif);
            porcentaje = itemView.findViewById(R.id.textViewPorRecDif);
            masomenos = itemView.findViewById(R.id.textViewMoMRecDif);
        }
    }
}
