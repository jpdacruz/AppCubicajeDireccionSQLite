package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.SiloBolsa;

import java.util.ArrayList;

public class AdapterSb extends RecyclerView.Adapter<AdapterSb.ViewHolder>
                        implements View.OnClickListener{

    ArrayList<SiloBolsa> siloBolsa;
    private View.OnClickListener listener;

    public AdapterSb(ArrayList<SiloBolsa> siloBolsa) {

        this.siloBolsa = siloBolsa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_listasb,null,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(String.format("ID SB: %s", siloBolsa.get(position).getId()));
        holder.tipoGrano.setText(String.format("Grano: %s", siloBolsa.get(position).getTipoGrano()));
        holder.phGrano.setText(String.format("PH: %s", String.valueOf(siloBolsa.get(position).getpHgrano())));
        holder.largo.setText(String.format("Largo: %s mts", String.valueOf(siloBolsa.get(position).getLargoSB())));
        holder.ancho.setText(String.format("Ancho: %s mts", String.valueOf(siloBolsa.get(position).getAnchoSB())));
        holder.base.setText(String.format("Base: %s mts", String.valueOf(siloBolsa.get(position).getAlturaBase())));
        holder.parabola.setText(String.format("Parabola: %s mts", String.valueOf(siloBolsa.get(position).getAlturaParabola())));
        holder.tons.setText(String.valueOf(siloBolsa.get(position).getToneladasSB()));
    }

    @Override
    public int getItemCount() {

        return siloBolsa.size();
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

        TextView id,tipoGrano, phGrano, largo,ancho, base, parabola, tons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewIdLSsb);
            tipoGrano = itemView.findViewById(R.id.textViewTGLSsb);
            phGrano = itemView.findViewById(R.id.textViewPHLSsb);
            largo = itemView.findViewById(R.id.textViewLarLSsb);
            ancho = itemView.findViewById(R.id.textViewAncSLSsb);
            base = itemView.findViewById(R.id.textViewBaseLSsb);
            parabola = itemView.findViewById(R.id.textViewParaLSsb);
            tons = itemView.findViewById(R.id.textViewTonSLsb);
        }
    }
}
