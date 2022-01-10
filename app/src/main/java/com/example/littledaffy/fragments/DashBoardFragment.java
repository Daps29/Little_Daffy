package com.example.littledaffy.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.littledaffy.MainActivity;
import com.example.littledaffy.R;
import com.example.littledaffy.adapter.CategoriesAdapter;
import com.example.littledaffy.adapter.ListaInicialAdapter;
import com.example.littledaffy.model.CategoriasDto;
import com.example.littledaffy.model.MascotaDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashBoardFragment() {
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

    ArrayList<CategoriasDto> categoriesAdapterArrayList;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView rv_categorias;
    RecyclerView.LayoutManager categories_layoutManager;


    RecyclerView rv_mascotas;
    DatabaseReference database;
    ListaInicialAdapter listaInicialAdapter;
    ArrayList<MascotaDto> mascotaDtoArrayList;
    RecyclerView.LayoutManager layoutManager;

    ConstraintLayout progress_bar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);



        progress_bar = (ConstraintLayout) root.findViewById(R.id.progress_bar);

        //CATEGORIAS MASCOTAS
        rv_categorias = (RecyclerView) root.findViewById(R.id.rv_categorias);
        rv_categorias.setHasFixedSize(true);
        categories_layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_categorias.setLayoutManager(categories_layoutManager);

        categoriesAdapterArrayList = new ArrayList<>();
        CategoriasDto categoria1 = new CategoriasDto(0,"Gatos", getResources().getDrawable(R.drawable.cate));
        CategoriasDto categoria2 = new CategoriasDto(1,"Perros", getResources().getDrawable(R.drawable.cate2));
        CategoriasDto categoria3 = new CategoriasDto(2,"Conejos", getResources().getDrawable(R.drawable.cate));
        CategoriasDto categoria4 = new CategoriasDto(3,"Aves", getResources().getDrawable(R.drawable.cate));
        CategoriasDto categoria5 = new CategoriasDto(4,"Hamsters", getResources().getDrawable(R.drawable.cate));
        CategoriasDto categoria6 = new CategoriasDto(5,"Otros", getResources().getDrawable(R.drawable.cate));
        categoriesAdapterArrayList.add(categoria1);
        categoriesAdapterArrayList.add(categoria2);
        categoriesAdapterArrayList.add(categoria3);
        categoriesAdapterArrayList.add(categoria4);
        categoriesAdapterArrayList.add(categoria5);
        categoriesAdapterArrayList.add(categoria6 );

        categoriesAdapter = new CategoriesAdapter(categoriesAdapterArrayList);
        rv_categorias.setAdapter(categoriesAdapter);


        //LISTA PRINCIPAL
        rv_mascotas = (RecyclerView) root.findViewById(R.id.rv_mascotas);
        rv_mascotas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_mascotas.setLayoutManager(layoutManager);
        //ACCIONES PARA LA LISTA
        database = FirebaseDatabase.getInstance().getReference("mascotas");
        mascotaDtoArrayList = new ArrayList<>();
        listaInicialAdapter = new ListaInicialAdapter(getContext(), mascotaDtoArrayList);
        rv_mascotas.setAdapter(listaInicialAdapter);


        //ACTUALIZAR LISTA
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateList();
            }
        });

        updateMascotasList();


        return root;

    }

    private void updateList(){
        updateMascotasList();
        swipeRefreshLayout.setRefreshing(false);
    }


    private void updateMascotasList(){
        progress_bar.setVisibility(View.VISIBLE);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mascotaDtoArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MascotaDto mascotaDto = dataSnapshot.getValue(MascotaDto.class);
                    int estado = mascotaDto.getEstado();
                    if (estado == 1) {
                        progress_bar.setVisibility(View.GONE);
                        mascotaDtoArrayList.add(mascotaDto);
                    }else{
                        return;
                    }

                }

                listaInicialAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
