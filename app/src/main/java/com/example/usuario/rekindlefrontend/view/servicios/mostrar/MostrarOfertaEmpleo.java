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
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.utils.Maps;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarOfertaEmpleo extends Maps implements OnMapReadyCallback {


    public MostrarOfertaEmpleo() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, numero, puesto, requisitos, jornada, horas, duracion,
            valoracion;
    AppCompatButton chat, opiniones, inscribirse;

    public OfertaEmpleo servicio;
    public MapFragment mMapView;
    public GoogleMap mGoogleMap;
    public Marker myMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_oferta_empleo, container,
                false);

        super.onCreate(savedInstanceState);

        titulo = view.findViewById(R.id.titulo_oferta_empleo);
        descripcion = view.findViewById(R.id.descripcion_oferta_empleo);
        direccion = view.findViewById(R.id.direccion_oferta_empleo);
        puesto = view.findViewById(R.id.puesto_oferta_empleo);
        requisitos = view.findViewById(R.id.requisitos_oferta_empleo);
        jornada = view.findViewById(R.id.jornada_oferta_empleo);
        horas = view.findViewById(R.id.horas_semanales_oferta_empleo);
        duracion = view.findViewById(R.id.duracion_oferta_empleo);
        mMapView = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.google_mapView);
        numero = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        valoracion = view.findViewById(R.id.valoracion_servicio);
        opiniones = view.findViewById(R.id.opiniones);
        inscribirse = view.findViewById(R.id.inscribirse);

        OfertaEmpleo servicio = (OfertaEmpleo) getActivity().getIntent().getSerializableExtra("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        puesto.setText(servicio.getPuesto());
        requisitos.setText(servicio.getRequisitos());
        jornada.setText(servicio.getJornada());
        horas.setText(servicio.getHorasSemana());
        duracion.setText(servicio.getDuracion());
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
