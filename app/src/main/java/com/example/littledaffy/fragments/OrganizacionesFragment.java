package com.example.littledaffy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littledaffy.R;
import com.example.littledaffy.adapter.OrganizacionAdapter;
import com.example.littledaffy.model.OrganizacionDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrganizacionesFragment extends Fragment {

    RecyclerView rv_subcategoria;
    DatabaseReference database;
    OrganizacionAdapter organizacionAdapter;
    ArrayList<OrganizacionDto> organizacionDtoList;

    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_organizaciones, container, false);

        //Para la lista organizaciones
        rv_subcategoria = (RecyclerView) root.findViewById(R.id.rv_organizaciones);
        rv_subcategoria.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rv_subcategoria.setLayoutManager(layoutManager);

        //ACCIONES PARA LA LISTA
        database = FirebaseDatabase.getInstance().getReference("organizaciones");

        organizacionDtoList = new ArrayList<>();
        organizacionAdapter = new OrganizacionAdapter(getContext(),organizacionDtoList);
        rv_subcategoria.setAdapter(organizacionAdapter);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                organizacionDtoList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrganizacionDto organizacionDto = dataSnapshot.getValue(OrganizacionDto.class);
                    int estado = organizacionDto.getEstado_organizacion();
                    if (estado == 1) {
                        organizacionDtoList.add(organizacionDto);
                    }else{
                        return;
                    }

                }

                organizacionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }

}
