package com.example.littledaffy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littledaffy.R;
import com.example.littledaffy.adapter.LogrosAdapter;
import com.example.littledaffy.model.MascotaDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentEncontrados extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEncontrados() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentEncontrados newInstance(String param1, String param2) {
        FragmentEncontrados fragment = new FragmentEncontrados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rv_subcategoria;
    DatabaseReference database;
    LogrosAdapter logrosAdapter;
    ArrayList<MascotaDto> mascotaDtoArrayList;

    RecyclerView.LayoutManager layoutManager;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_encontrados, container, false);

        //Para la lista organizaciones
        rv_subcategoria = (RecyclerView) root.findViewById(R.id.rv_encontrados);
        rv_subcategoria.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rv_subcategoria.setLayoutManager(layoutManager);

        //ACCIONES PARA LA LISTA
        database = FirebaseDatabase.getInstance().getReference("mascotas");

        mascotaDtoArrayList = new ArrayList<>();
        logrosAdapter = new LogrosAdapter(getContext(), mascotaDtoArrayList);
        rv_subcategoria.setAdapter(logrosAdapter);

        //ACTUALIZAR LISTA
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateList();
            }
        });


        updateMascotasEncontrados();

        return root;
    }

    private void updateList(){
        updateMascotasEncontrados();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void updateMascotasEncontrados(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    mascotaDtoArrayList.clear();
                    MascotaDto mascotaDto = dataSnapshot.getValue(MascotaDto.class);
                    int estado = mascotaDto.getEstadoperdida();
                    if (estado == 1) {
                        mascotaDtoArrayList.add(mascotaDto);
                    }else{
                        return;
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