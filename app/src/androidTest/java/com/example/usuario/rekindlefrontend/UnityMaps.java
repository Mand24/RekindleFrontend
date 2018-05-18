package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;
import android.net.NetworkInfo;

import com.example.usuario.rekindlefrontend.utils.Maps;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.junit.Test;

public class UnityMaps {

    private String adress = "Carrer Torrent Roig, 08348 Cabrils, Barcelona";
    private Maps maps = new Maps ();
    private GoogleMap mGoogleMap;
    Marker mres;
    Context c;

    @Test
    public void testMyMarker ()
    {
        mres = maps.setMarker(adress, mres, mGoogleMap);
        assertEquals (mres.getTitle(), "Localización Servicio");
        LatLng coordenadas = maps.getLocationFromAddress (c.getApplicationContext (),
                adress);
        assertTrue(coordenadas.equals(mres.getPosition()));
    }

    @Test
    public void testNetWork ()
    {
        assertTrue (maps.getNetworkInfo() instanceof  NetworkInfo);
    }
}
