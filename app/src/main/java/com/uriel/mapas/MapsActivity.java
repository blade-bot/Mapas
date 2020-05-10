package com.uriel.mapas;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker marca_tec;
    private GoogleMap Mapa_actual;
    Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        b1 = findViewById(R.id.boton_nomrmal);
        b2 = findViewById(R.id.boton_hibrido);
        b3 = findViewById(R.id.boton_satelital);
        b4 = findViewById(R.id.boton_terreno);
        //listener de los botones
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mapa_actual.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mapa_actual.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mapa_actual.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mapa_actual.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //agregar el mapa actual a la variable local para acceder por los listeners
        Mapa_actual = googleMap;
        LatLng Tec = new LatLng(23.6688, -100.63);
        marca_tec = googleMap.addMarker(new MarkerOptions().position(Tec).title("Tecnológico de Matehuala"));
        googleMap.setOnMarkerClickListener(this);
        //mover la camara a mis marcadores
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Real, 8));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String lat, lon;
        lat = Double.toString(marker.getPosition().latitude);
        lon = Double.toString(marker.getPosition().longitude);
        if (marker.equals(marca_tec))
            Toast.makeText(this,"Tecnológico de Matehuala\n Latitud: "+lat+"  Longitud: "+lon, Toast.LENGTH_SHORT).show();

        return false;
    }
}
