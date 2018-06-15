package com.example.usuario.rekindlefrontend;

import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.RefugeeAdapter;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RefugeeAdapterUnity {

    private List<Refugee> refugees;
    private CustomItemClickListener listener;
    private Context context;

    private RefugeeAdapter refugeeAdapter;

    @Before
    public void before() {
        refugees = new ArrayList<Refugee>();
        Refugee r1 = new Refugee();
        Refugee r2 = new Refugee();

        refugees.add(r1);
        refugees.add(r2);

        refugeeAdapter = new RefugeeAdapter(context, refugees, listener);
    }

    @Test
    public void testConstructor() {
        refugeeAdapter = new RefugeeAdapter(context, refugees, listener);

        assertNotNull(refugeeAdapter);
    }

    @Test
    public void testCount() {
        int count = refugeeAdapter.getItemCount();

        assertEquals("r1 + r2 = 2", count, 2);
    }

    @Test
    public void testGetSet() {
        List<Refugee> newList = new ArrayList<Refugee>();
        refugeeAdapter.setRefugees(newList);

        assertEquals(newList, refugeeAdapter.getRefugees());
    }
}
