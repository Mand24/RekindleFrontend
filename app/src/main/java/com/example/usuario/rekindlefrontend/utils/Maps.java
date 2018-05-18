package com.example.usuario.rekindlefrontend.utils;

import android.app.Fragment;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.usuario.rekindlefrontend.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Maps extends Fragment {

    public NetworkInfo getNetworkInfo ()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity ().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();

         /*System.out.println ("Available: " +network.isAvailable ()+ "---");
         System.out.println ("Failover: " +network.isFailover ()+ "---");
         System.out.println ("Connected: " +network.isConnected ()+ "---");
         System.out.println ("Network info:"+ "---");
         System.out.println ("Type: " +network.getTypeName()+ "---");
         System.out.println ("State: " +network.getDetailedState()+ "---");
         System.out.println ("Extra info: " +network.getExtraInfo()+ "---");*/

        return network;
    }

    public Marker setMarker (String adress, Marker myMarker, GoogleMap
            mGoogleMap) {

        // set : coordenadas
        LatLng coordenadas = getLocationFromAddress (getActivity ().getApplicationContext (),
                adress);

        // set : location
        CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom (coordenadas, 16);

        // set : marker
        if (myMarker != null) myMarker.remove ();

        myMarker = mGoogleMap.addMarker (new MarkerOptions().position (coordenadas).title
                ("Localización Servicio").icon (BitmapDescriptorFactory.fromResource (R.mipmap
                .ic_launcher)));

        //  move camera
        mGoogleMap.animateCamera (myLocation);

        return myMarker;
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
