package com.example.usuario.rekindlefrontend.view.servicios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Servicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostrarServicio extends AppCompatActivity implements OnMapReadyCallback {

    TextView titulo, descripcion, direccion, fecha, numero, valoracion;
    SupportMapFragment mMapView;
    GoogleMap mGoogleMap;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        titulo = (TextView) findViewById(R.id.titulo_servicio);
        descripcion = (TextView) findViewById(R.id.descripcion_servicio);
        direccion = (TextView) findViewById(R.id.direccion_servicio);
        fecha = (TextView) findViewById(R.id.fecha_limite_servicio);
        mMapView = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_mapView);
        numero = (TextView) findViewById(R.id.numero_contacto_servicio);
        chat = (AppCompatButton) findViewById(R.id.chat);
        valoracion = (TextView) findViewById(R.id.valoracion_servicio);
        opiniones = (AppCompatButton) findViewById(R.id.opiniones);
        inscribirse = (AppCompatButton) findViewById(R.id.inscribirse);

        Servicio servicio = (Servicio) getIntent().getSerializableExtra("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        fecha.setText(servicio.getFecha());
        numero.setText(servicio.getNumero());
        valoracion.setText("Valoracion: " + servicio.getValoracion());

        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
