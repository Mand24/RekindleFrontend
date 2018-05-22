package com.example.usuario.rekindlefrontend.view.servicios;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarServicio extends AppCompatActivity implements OnMapReadyCallback {

    private Refugiado refugiado = new Refugiado ("refugiado@gmail.com", "pass1234",
            "refugiadoName", "refugiadoSurname","refugadioSecondSurname", "123456789",
            "12-09-2018","Male","Barcelona","cabrils","ethinc","+A","","");

    private CursoEducativo ser0 = new CursoEducativo (0, "voluntario@gmail.com",
            "cursoEducativo", "descr", "Passatge Passalaigua, 14, 08348 Cabrils, Barcelona", "ambito", "requisits", "horario", "plazas", "123,2", "12");
    private Alojamiento    ser1 = new Alojamiento    (1, "voluntario@gmail.com", "alojamiento",
            "desc", "Carrer Infern d'en Parera, 2, 08348 Cabrils, Barcelona", "12","12-03-2018", "1234");
    private OfertaEmpleo   ser2 = new OfertaEmpleo   (2, "voluntario@gmail.com", "empleo", "desc",
            "Carrer Bellesguard, 23-7, 08348 Cabrils, Barcelona", "puesto", "requi", "jornada", "12", "50", "120", "1200", "2345");
    private Donacion       ser3 = new Donacion       (3, "voluntario@gmail.com", "donacion",
            "desc", "Carrer Torrent de Can Cama, 7-5, 08348 Cabrils, Barcelona", "123", "12:00", "13:00", "123445");

    private ArrayList <Servicio> servicios = new ArrayList <Servicio> ();

    private MapFragment mapFragment;
    private GoogleMap   map;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        servicios.add (ser0);
        servicios.add (ser1);
        servicios.add (ser2);
        servicios.add (ser3);

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buscar_servicio);

        mapFragment = (MapFragment) getFragmentManager ().findFragmentById (R.id.google_mapView_buscarServicio);
        mapFragment.getMapAsync (this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        NetworkInfo network = getNetworkInfo ();

        if (network != null && network.isConnectedOrConnecting ()) {
            try {

                setServicies();
                setMyPosition();

            } catch (Exception e) // connected but no internet (login required, for exemple)
            {
                Toast.makeText(getApplicationContext (), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext (), getString (R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void setMyPosition()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext (), getString(R.string.no_permission), Toast
                    .LENGTH_LONG).show();
        }
        else {
            try {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location myLocation = locationManager.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER);
                LatLng userLocation = new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());

                map.addMarker(new MarkerOptions().position(userLocation).title
                        (getString(R.string.myLocation_maps)));

                // set : location
                CameraUpdate camera = CameraUpdateFactory.newLatLngZoom (userLocation, 9);

                //  move camera
                map.animateCamera (camera);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext (), getString(R.string.noLocation), Toast
                        .LENGTH_LONG).show();
            }
        }
    }

    public void setServicies ()
    {
        for (Servicio s : servicios) {
            setMarkerService(s.getDireccion(), map, s.getNombre());
        }
    }

    public NetworkInfo getNetworkInfo ()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();

        return network;
    }

    public void setMarkerService (String adress, GoogleMap
            mGoogleMap, String serviceName) {

        // set : coordenadas
        LatLng coordenadas = getLocationFromAddress (getApplicationContext (),
                adress);

        Marker myMarker = mGoogleMap.addMarker (new MarkerOptions().position (coordenadas).title
                (getString(R.string.serviceLocation) + " " + serviceName));
    }

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }
}