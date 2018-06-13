package com.example.usuario.rekindlefrontend;

import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.ServicesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapterUnity {
    private List<Service> services;
    private Context context;
    private CustomItemClickListener listener;

    private ServicesAdapter serviceAdapter;

    @Before
    public void before() {
        services = new ArrayList<Service>();
        Service s1 = new Service(1,"Lodge", "email@email.com", "service lodge", "decription",
                "carrer", "12134");
        Service s2 = new Service(2,"Lodge", "email2@email.com", "service lodge2", "decription",
                "carrer dos", "1211234");

        services.add(s1);
        services.add(s2);

        serviceAdapter = new ServicesAdapter(context, services, listener);
    }

    @Test
    public void testConstrutor() {
        serviceAdapter = new ServicesAdapter(context, services, listener);

        assertNotNull(serviceAdapter);
    }

    @Test
    public void testCount () {
        int count = serviceAdapter.getItemCount();

        assertEquals("s1 + s2", count, serviceAdapter.getItemCount());
    }

    @Test
    public void testGetSet()
    {
        List<Service> newList = new ArrayList<Service>();
        serviceAdapter.setServices(newList);

        assertEquals(newList, serviceAdapter.getServices());
    }
}
