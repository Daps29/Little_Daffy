package com.example.littledaffy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littledaffy.model.CategoriasDto;
import com.example.littledaffy.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyCategoriesViewHolder> {

    private ArrayList<CategoriasDto> data;
    int row_index = -1;

    public static class MyCategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView categoriaName;
        ImageView categoriaImg;
        CardView cardView;
        public MyCategoriesViewHolder(View v) {
            super(v);
            categoriaName = (TextView) v.findViewById(R.id.categoriaName);
            categoriaImg = (ImageView) v.findViewById(R.id.categoriaImg);
            cardView = (CardView) v.findViewById(R.id.categoria_mascota);
        }
    }

    public CategoriesAdapter(ArrayList<CategoriasDto> myDataSet){
        data = myDataSet;
    }

    @NonNull
    @Override
    public MyCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_categoria, parent, false);
        MyCategoriesViewHolder vh = new MyCategoriesViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoriesViewHolder holder, int position) {
        CategoriasDto currentItem = data.get(position);
        holder.categoriaName.setText(currentItem.getNombre_categoria());
        holder.categoriaImg.setImageDrawable(currentItem.getFoto_categoria());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position){
            holder.cardView.setBackgroundResource(R.drawable.categoria_selected);
        }else{
            holder.cardView.setBackgroundResource(R.drawable.shape);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
