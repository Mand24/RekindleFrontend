package com.example.usuario.rekindlefrontend.view.services;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.services.list.ListServices;
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

public class SearchService extends AppBaseActivity implements OnMapReadyCallback {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private ArrayList<Service> mServices = new ArrayList<Service>();
    private MapFragment mapFragment;
    private GoogleMap map;
    private LocationManager locationManager;
    private APIService mAPIService = APIUtils.getAPIService();
    private AppCompatButton mButton;
    private boolean cameraAssigned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        mButton = findViewById(R.id.listServices);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListServices.class);
                startActivity(intent);
            }
        });

        mAPIService.obtenerServicios().enqueue(new Callback<ArrayList<Service>>() {
            @Override
            public void onResponse(Call<ArrayList<Service>> call,
                    Response<ArrayList<Service>> response) {
                if (response.isSuccessful()) {
                    mServices = response.body();
                    setPositions();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R
                            .string.error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Service>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R
                        .string.error), Toast.LENGTH_SHORT).show();
            }
        });

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(
                R.id.google_mapView_buscarServicio);
        mapFragment.getMapAsync(this);

    }


    private void setPositions() {
        NetworkInfo network = getNetworkInfo();

        if (network != null && network.isConnectedOrConnecting()) {
            try {
                if (checkLocationPermission()) {
                    setMyPosition();
                }
                setServices();

            } catch (Exception e) // connected but no internet (login required, for exemple)
            {
                Toast.makeText(getApplicationContext(), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ListServices.class);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        NetworkInfo network = getNetworkInfo();

        if (network != null && network.isConnectedOrConnecting()) {
            try {

                setServices();
                setMyPosition();

            } catch (Exception e) // connected but no internet (login required, for exemple)
            {
                Toast.makeText(getApplicationContext(), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void setMyPosition() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_permission), Toast
                    .LENGTH_LONG).show();
        } else {
            try {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location myLocation = locationManager.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER);

                System.out.println("No location :(");

                LatLng userLocation = new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());
                map.addMarker(new MarkerOptions().position(userLocation).title
                        (getString(R.string.myLocation_maps)));

                // set : location
                CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(userLocation, 9);

                //  move camera
                map.animateCamera(camera);
                cameraAssigned = true;

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.noLocation), Toast
                        .LENGTH_LONG).show();
            }
        }
    }

    public void setServices() {
        if (mServices.size() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.noServices), Toast
                    .LENGTH_LONG).show();
        }
        for (Service s : mServices) {
            setMarkerService(s.getAdress(), map, s.getName());
        }
    }

    public NetworkInfo getNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();

        return network;
    }

    public void setMarkerService(String adress, GoogleMap
            mGoogleMap, String serviceName) {

        // set : coordenadas

        LatLng coordinates = getLocationFromAddress(getApplicationContext(),
                adress);

        Marker myMarker = mGoogleMap.addMarker(new MarkerOptions().position(coordinates).title

                (getString(R.string.serviceLocation) + " " + serviceName));

        if (!cameraAssigned) {
            CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(coordinates, 9);
            map.animateCamera(camera);
            cameraAssigned = true;
        }
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

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

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setMyPosition();

                }
            }
        }
    }

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = locationManager.getLastKnownLocation(
                    provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}