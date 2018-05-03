package com.example.usuario.rekindlefrontend.view.servicios.mostrar;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.entity.Servicio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarDonacion extends Fragment implements OnMapReadyCallback {


    public MostrarDonacion() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, inicio, fin, numero, valoracion;
    MapFragment mMapView;
    GoogleMap mGoogleMap;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_donacion, container,
                false);

        super.onCreate(savedInstanceState);

        titulo = (TextView) view.findViewById(R.id.titulo_donacion);
        descripcion = (TextView) view.findViewById(R.id.descripcion_donacion);
        direccion = (TextView) view.findViewById(R.id.direccion_donacion);
        inicio = (TextView) view.findViewById(R.id.hora_inicio_donacion);
        fin = (TextView) view.findViewById(R.id.hora_fin_donacion);
        mMapView = (MapFragment) getFragmentManager().findFragmentById(R.id.google_mapView);
        numero = (TextView) view.findViewById(R.id.numero_contacto_servicio);
        chat = (AppCompatButton) view.findViewById(R.id.chat);
        valoracion = (TextView) view.findViewById(R.id.valoracion_servicio);
        opiniones = (AppCompatButton) view.findViewById(R.id.opiniones);
        inscribirse = (AppCompatButton) view.findViewById(R.id.inscribirse);

        Servicio servicio = (Servicio) getActivity().getIntent().getSerializableExtra("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
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
