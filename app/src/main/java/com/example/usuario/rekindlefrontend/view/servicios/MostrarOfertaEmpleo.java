package com.example.usuario.rekindlefrontend.view.servicios;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Servicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarOfertaEmpleo extends Fragment {


    public MostrarOfertaEmpleo() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, numero, puesto, requisitos, jornada, horas, duracion,
            valoracion;
    SupportMapFragment mMapView;
    GoogleMap mGoogleMap;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_oferta_empleo, container,
                false);

        super.onCreate(savedInstanceState);

        titulo = (TextView) view.findViewById(R.id.titulo_oferta_empleo);
        descripcion = (TextView) view.findViewById(R.id.descripcion_oferta_empleo);
        direccion = (TextView) view.findViewById(R.id.direccion_oferta_empleo);
        puesto = (TextView) view.findViewById(R.id.puesto_oferta_empleo);
        requisitos = (TextView) view.findViewById(R.id.requisitos_oferta_empleo);
        jornada = (TextView) view.findViewById(R.id.jornada_oferta_empleo);
        horas = (TextView) view.findViewById(R.id.horas_semanales_oferta_empleo);
        duracion = (TextView) view.findViewById(R.id.duracion_oferta_empleo);
        mMapView = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_mapView);
        numero = (TextView) view.findViewById(R.id.numero_contacto_servicio);
        chat = (AppCompatButton) view.findViewById(R.id.chat);
        valoracion = (TextView) view.findViewById(R.id.valoracion_servicio);
        opiniones = (AppCompatButton) view.findViewById(R.id.opiniones);
        inscribirse = (AppCompatButton) view.findViewById(R.id.inscribirse);

        Servicio servicio = (Servicio) getIntent().getSerializableExtra("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        fecha.setText(servicio.getFecha());
        numero.setText(servicio.getNumero());
        valoracion.setText("Valoracion: " + servicio.getValoracion());

        mMapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
