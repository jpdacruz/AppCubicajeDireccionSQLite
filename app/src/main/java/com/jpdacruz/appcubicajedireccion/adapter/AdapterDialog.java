package com.jpdacruz.appcubicajedireccion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Estructura;
import java.util.ArrayList;

public class AdapterDialog
        extends RecyclerView.Adapter<AdapterDialog.ViewHolder>
        implements View.OnClickListener {

    ArrayList<Estructura> estructura;

    View.OnClickListener listener;

    public AdapterDialog(ArrayList<Estructura> estructura) {
        this.estructura = estructura;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.item_dialog_elegir,null,false);

        view.setOnClickListener(this);

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDialog.ViewHolder holder, int position) {

        holder.imageView.setImageResource(estructura.get(position).getImagenEstructura());
        holder.textView.setText(estructura.get(position).getEstructura());
        holder.flecha.setImageResource(estructura.get(position).getImagenFlecha());
    }

    @Override
    public int getItemCount() {
        return estructura.size();
    }

    public void setOnClickListener (View.OnClickListener listener){

        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if(listener!=null){

            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageView flecha;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        imageView = itemView.findViewById(R.id.imagen_itemRec);
        flecha = itemView.findViewById(R.id.arrow_to_silo);
        textView = itemView.findViewById(R.id.to_add_silo);

        }
    }
}
