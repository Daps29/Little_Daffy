package com.example.littledaffy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.littledaffy.databinding.ActivityMapsBinding;
import com.example.littledaffy.model.DireccionDto;
import com.example.littledaffy.model.OrganizacionDto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerOrganizacionesActivity extends AppCompatActivity implements OnMapReadyCallback {

    String organizacionId;
    DatabaseReference organizacionInfo;
    TextView nombreOrganizacion, descripcionOrganizacion, horario, direccionLiteral;
    CircleImageView profile_image;
    ImageView fotoPortada;
    Double latitude, longitude;
    String nombremarcador, iddireccion;
    FloatingActionButton whatsapp;
    String celOrga;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_organizacion);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.secundary), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);

        //GOOGLE MAPS
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //OBTENER DATOS DE LA ANTERIOR ACTIVITY
        Intent intent = getIntent();
        organizacionId = intent.getStringExtra("id_organizacion");
        iddireccion = intent.getStringExtra("direccion");
        organizacionInfo = FirebaseDatabase.getInstance().getReference("organizaciones").child(organizacionId);





        nombreOrganizacion = (TextView) findViewById(R.id.nombreOrganizacion);
        descripcionOrganizacion = (TextView) findViewById(R.id.descripcionOrganizacion);
        horario = (TextView) findViewById(R.id.horario);
        direccionLiteral = (TextView) findViewById(R.id.direccionLiteral);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        fotoPortada = (ImageView) findViewById(R.id.fotoPortadaOrganizacion);
        whatsapp = (FloatingActionButton) findViewById(R.id.whatsapp);

        //RECUPERAMOS INFORMACION PARA LLENAR EL ACTIVITY
        organizacionInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OrganizacionDto organizacionDto = dataSnapshot.getValue(OrganizacionDto.class);
                nombreOrganizacion.setText(organizacionDto.getNombre());
                descripcionOrganizacion.setText(organizacionDto.getDescripcion());
                horario.setText(organizacionDto.getHoraen() + " " + organizacionDto.getHorafin());
                direccionLiteral.setText(organizacionDto.getDireccion_literal());
                celOrga = organizacionDto.getContacto()+"";
                Picasso.get().load(organizacionDto.getFoto()).placeholder(R.drawable.a).into(profile_image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("PICASSO ERROR", "onError: " + e);
                    }
                });
                Picasso.get().load(organizacionDto.getFoto_portada()).placeholder(R.drawable.a).fit().into(fotoPortada, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("PICASSO ERROR", "onError: " + e);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //BOTON WHATSAPP
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String celOrganizacion = celOrga;
                if (celOrganizacion.substring(0, 3).equals("591")) {
                    celOrganizacion = celOrganizacion.substring(3);
                }

                if (celOrganizacion.substring(0, 4).equals("+591")) {
                    celOrganizacion = celOrganizacion.substring(4);
                }

                String mensaje = "Buenas!Soy repartidor de la App Ayni Chef, tengo la siguiente duda: ";
                String url = "https://wa.me/591" + celOrganizacion + "?text=" + mensaje;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        FirebaseDatabase.getInstance().getReference("direcciones").child(iddireccion)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            mMap = googleMap;
                            DireccionDto direccionDto = dataSnapshot.getValue(DireccionDto.class);
                            //VALIDAMOS Y RECUPERAMOS UBICACION
                            latitude = Double.valueOf(direccionDto.getLatitud());
                            longitude = Double.valueOf(direccionDto.getLongitud());

                            FirebaseDatabase.getInstance().getReference("organizaciones").child(direccionDto.getIduser())
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                mMap = googleMap;
                                                OrganizacionDto organizacionDto = dataSnapshot.getValue(OrganizacionDto.class);
                                                nombremarcador = organizacionDto.getNombre();
                                                //Agrega marcador de la ubicacion actual en el mapa
                                                if(latitude != null && longitude != null){
                                                    LatLng ubicacionActual = new LatLng(latitude, longitude);
                                                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                                                            (R.drawable.ic_marcador)).anchor(0.0f , 1.0f).position(ubicacionActual).title(nombremarcador));
                                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 15));

                                                }else{
                                                    latitude = -16.5435752;
                                                    longitude = -68.0591915;
                                                    LatLng ubicacionLaPaz = new LatLng(latitude, longitude);
                                                    mMap.addMarker(new MarkerOptions().position(ubicacionLaPaz).title("Ubicación de la organizacion no registrada"));
                                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionLaPaz, 15));
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }
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
