package com.example.usuario.rekindlefrontend;

import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.ReportsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReportsAdapterUnity {
    private List<Report> reports;
    private CustomItemClickListener listener;
    private Context context;

    private ReportsAdapter reportsAdapter;

    @Before
    public void before() {
        reports = new ArrayList<Report>();
        Report r1 = new Report();
        Report r2 = new Report();

        reports.add(r1);
        reports.add(r2);

        reportsAdapter = new ReportsAdapter (context, reports, listener);
    }

    @Test
    public void testConstructor() {
        reportsAdapter = new ReportsAdapter (context, reports, listener);

        assertNotNull(reportsAdapter);
    }

    @Test
    public void testCount() {
        int count = reportsAdapter.getItemCount();

        assertEquals("r1 + r2 = 2", count, 2);
    }

    @Test
    public void testGetSet() {
        List<Report> newList = new ArrayList<Report> ();
        reportsAdapter.setReports(newList);

        assertEquals(newList, reportsAdapter.getReports());
    }
}
