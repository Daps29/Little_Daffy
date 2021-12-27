package com.example.littledaffy;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littledaffy.adapter.OrganizacionAdapter;
import com.example.littledaffy.model.OrganizacionDto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrganizacionesActivity extends AppCompatActivity {

    RecyclerView rv_subcategoria;
    DatabaseReference database;
    OrganizacionAdapter organizacionAdapter;
    ArrayList<OrganizacionDto> organizacionDtoList;

    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizaciones);



        /**Para la lista organizaciones*/
        rv_subcategoria = (RecyclerView) findViewById(R.id.rv_organizaciones);
        rv_subcategoria.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_subcategoria.setLayoutManager(layoutManager);

        //ACCIONES PARA LA LISTA
        database = FirebaseDatabase.getInstance().getReference("organizaciones");

        organizacionDtoList = new ArrayList<>();
        organizacionAdapter = new OrganizacionAdapter(this,organizacionDtoList);
        rv_subcategoria.setAdapter(organizacionAdapter);

        organizacionDtoList.clear();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrganizacionDto organizacionDto = dataSnapshot.getValue(OrganizacionDto.class);
                    int estado = organizacionDto.getEstado_organizacion();
                    if (estado == 1) {
                        organizacionDtoList.add(organizacionDto);
                    }else {
                        return;
                    }

                }

                organizacionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
