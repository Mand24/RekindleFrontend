package com.example.usuario.rekindlefrontend.view.servicios.mostrar;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarAlojamiento extends Fragment implements OnMapReadyCallback {


    public MostrarAlojamiento() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, fecha, numero, valoracion;
    MapFragment mMapView;
    GoogleMap mGoogleMap;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_alojamiento, container,
                false);

        super.onCreate(savedInstanceState);

        titulo = view.findViewById(R.id.titulo_alojamiento);
        descripcion = view.findViewById(R.id.descripcion_alojamiento);
        direccion = view.findViewById(R.id.direccion_alojamiento);
        fecha = view.findViewById(R.id.fecha_limite_alojamiento);
        mMapView = (MapFragment) getFragmentManager().findFragmentById(R.id.google_mapView);
        numero = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        valoracion = view.findViewById(R.id.valoracion_servicio);
        opiniones = view.findViewById(R.id.opiniones);
        inscribirse = view.findViewById(R.id.inscribirse);

        Alojamiento servicio = (Alojamiento) getActivity().getIntent().getSerializableExtra
                ("Servicio");

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
