package com.example.littledaffy.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littledaffy.OrganizacionesActivity;
import com.example.littledaffy.R;
import com.example.littledaffy.VerOrganizacionesActivity;
import com.example.littledaffy.model.OrganizacionDto;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrganizacionAdapter extends RecyclerView.Adapter<OrganizacionAdapter.MyViewHolder> {

    private ArrayList<OrganizacionDto> data;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, horafin, horaen, descripcion, contacto, direccion_literal;
        ImageView foto;
        Context context;


        public MyViewHolder(@NonNull View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombreOrganizacion);
            horaen = (TextView) v.findViewById(R.id.entrada);
            horafin = (TextView) v.findViewById(R.id.salida);
            direccion_literal = (TextView) v.findViewById(R.id.ubicacionOrganizacion);
            foto = (ImageView) v.findViewById(R.id.organizacionImage);
            context = v.getContext();
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OrganizacionAdapter(Context organizacionesActivity, ArrayList<OrganizacionDto> myDataset) {
        data = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public OrganizacionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_organizaciones, parent, false);

        OrganizacionAdapter.MyViewHolder vh = new OrganizacionAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull OrganizacionAdapter.MyViewHolder holder, int position) {
        final OrganizacionDto currentItem = data.get(position);

        holder.nombre.setText(currentItem.getNombre());
        holder.horaen.setText(currentItem.getHoraen());
        holder.horafin.setText(currentItem.getHorafin());
        holder.direccion_literal.setText(currentItem.getDireccion_literal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context.getApplicationContext(), VerOrganizacionesActivity.class);
                intent.putExtra("id_organizacion", currentItem.getId_organizacion());
                intent.putExtra("direccion", currentItem.getDireccion());
                holder.context.startActivity(intent);
            }
        });

        Picasso.get().load(currentItem.getFoto()).placeholder(R.drawable.a).into(holder.foto, new Callback() {
            @Override public void onSuccess() {

            }
            @Override
            public void onError(Exception e) {
                Log.e("PICASSO ERROR", "onError: "+ e);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
