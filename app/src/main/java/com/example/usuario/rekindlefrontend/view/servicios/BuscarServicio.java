package com.example.usuario.rekindlefrontend.view.servicios;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Alojamiento;
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Donacion;
import com.example.usuario.rekindlefrontend.data.entity.servicio.OfertaEmpleo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.servicios.listar.ListarServicios;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarServicio extends AppBaseActivity implements OnMapReadyCallback {

    private ArrayList <Servicio> servicios = new ArrayList <Servicio> ();

    private MapFragment mapFragment;
    private GoogleMap   map;

    private LocationManager locationManager;

    private APIService mAPIService = APIUtils.getAPIService();

    private AppCompatButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buscar_servicio);

        mButton = findViewById(R.id.listServices);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListarServicios.class);
                startActivity(intent);
            }
        });

        mAPIService.obtenerServicios().enqueue(new Callback<ArrayList<Servicio>>() {
            @Override
            public void onResponse(Call<ArrayList<Servicio>> call,
                    Response<ArrayList<Servicio>> response) {
                if (response.isSuccessful()){
                    servicios = response.body();
                }
                else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R
                            .string.error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Servicio>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R
                        .string.error), Toast.LENGTH_SHORT).show();
            }
        });

        mapFragment = (MapFragment) getFragmentManager ().findFragmentById (R.id.google_mapView_buscarServicio);
        mapFragment.getMapAsync (this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
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