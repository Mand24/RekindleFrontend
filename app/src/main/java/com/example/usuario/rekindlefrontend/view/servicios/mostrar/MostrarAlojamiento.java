package com.example.usuario.rekindlefrontend.view.servicios.mostrar;


import android.app.Fragment;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.utils.Maps;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarAlojamiento extends Maps implements OnMapReadyCallback {


    public MostrarAlojamiento() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, fecha, numero, valoracion;
    AppCompatButton chat, opiniones, inscribirse;

    public Alojamiento servicio;
    public MapFragment mMapView;
    public GoogleMap mGoogleMap;
    public Marker myMarker;


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

        servicio = (Alojamiento) getActivity().getIntent().getSerializableExtra
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

        mGoogleMap = googleMap;

        NetworkInfo network = getNetworkInfo ();

        if (network != null && network.isConnectedOrConnecting ()) {
            try {
                //setMarker(servicio.getDireccion (), myMarker, mGoogleMap);
                myMarker = setMarker("Carrer de l'Estronci, 41, 08906 L'Hospitalet de Llobregat, "
                        + "Barcelona", myMarker, mGoogleMap);
            } catch (Exception e) // Conectats però sense internet (p.e. falta logejar-nos)
            {
                Toast.makeText(getActivity ().getApplicationContext (), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity ().getApplicationContext (), getString (R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }
}