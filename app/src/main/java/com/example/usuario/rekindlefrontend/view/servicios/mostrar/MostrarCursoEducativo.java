package com.example.usuario.rekindlefrontend.view.servicios.mostrar;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarCursoEducativo extends Fragment implements OnMapReadyCallback {

    public MostrarCursoEducativo() {
        // Required empty public constructor
    }

    TextView titulo, descripcion, direccion, ambito, requisitos, horario, precio, numero,
            valoracion;
    MapFragment mMapView;
    GoogleMap mGoogleMap;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_curso_educativo, container,
                false);

        super.onCreate(savedInstanceState);

        titulo = view.findViewById(R.id.titulo_curso_educativo);
        descripcion = view.findViewById(R.id.descripcion_curso_educativo);
        direccion = view.findViewById(R.id.direccion_curso_educativo);
        ambito = view.findViewById(R.id.ambito_curso_educativo);
        requisitos = view.findViewById(R.id.requisitos_curso_educativo);
        horario = view.findViewById(R.id.horario_curso_educativo);
        precio = view.findViewById(R.id.precio_curso_educativo);
        mMapView = (MapFragment) getFragmentManager().findFragmentById(R.id.google_mapView);
        numero = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        valoracion = view.findViewById(R.id.valoracion_servicio);
        opiniones = view.findViewById(R.id.opiniones);
        inscribirse = view.findViewById(R.id.inscribirse);

        CursoEducativo servicio = (CursoEducativo) getActivity().getIntent().getSerializableExtra
                ("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        ambito.setText(servicio.getDireccion());
        requisitos.setText(servicio.getRequisitos());
        horario.setText(servicio.getHorario());
        precio.setText(servicio.getPrecio());
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
