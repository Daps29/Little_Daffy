package com.example.littledaffy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littledaffy.R;
import com.example.littledaffy.adapter.LogrosAdapter;
import com.example.littledaffy.model.MascotaDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LogrosFragment extends Fragment {

    RecyclerView rv_subcategoria;
    DatabaseReference database;
    LogrosAdapter logrosAdapter;
    ArrayList<MascotaDto> mascotaDtoArrayList;

    RecyclerView.LayoutManager layoutManager;
    Button encontrados, adoptados;
    TextView vacio, estado_logro;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_logros, container, false);

        adoptados = root.findViewById(R.id.btn_adopta);
        encontrados = root.findViewById(R.id.btn_encon);
        vacio = root.findViewById(R.id.vacio);
        estado_logro = root.findViewById(R.id.estado_logro);

        //Para la lista organizaciones
        rv_subcategoria = (RecyclerView) root.findViewById(R.id.rv_logros);
        rv_subcategoria.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rv_subcategoria.setLayoutManager(layoutManager);

        //ACCIONES PARA LA LISTA
        database = FirebaseDatabase.getInstance().getReference("mascotas");

        mascotaDtoArrayList = new ArrayList<>();
        logrosAdapter = new LogrosAdapter(getContext(), mascotaDtoArrayList);
        rv_subcategoria.setAdapter(logrosAdapter);


        adoptados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMascotasAdoptadas();
                vacio.setVisibility(View.GONE);
            }
        });

        encontrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vacio.setVisibility(View.GONE);
                updateMascotasEncontradas();
            }
        });

        return root;
    }

    private void updateMascotasEncontradas() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mascotaDtoArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MascotaDto mascotaDto = dataSnapshot.getValue(MascotaDto.class);
                    int estado = mascotaDto.getEstadoperdida();
                    if (estado == 1) {
                        mascotaDtoArrayList.add(mascotaDto);
                    }

                }

                logrosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateMascotasAdoptadas() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mascotaDtoArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MascotaDto mascotaDto = dataSnapshot.getValue(MascotaDto.class);
                    int estado = mascotaDto.getEstadoperdida();
                    if (estado == 0) {
                        mascotaDtoArrayList.add(mascotaDto);
                    }

                }

                logrosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
