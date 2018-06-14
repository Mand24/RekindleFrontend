package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Job;

import org.junit.Test;

public class JobUnity {

    private Job job = new Job (2, "voluntario@gmail.com", "job", "desc", "Carrer "
            + "Bellesguard, 23-7, 08348 Cabrils, Barcelona", "charge", "requi", "5", "12",
            "50", "120", "1200", "2345", false);

    @Test
    public void testGets () {
        assertEquals(job.getId(), 2);
        assertEquals(job.getName(), "job");
        assertEquals(job.getEmail(), "voluntario@gmail.com");
        assertEquals(job.getDescription(), "desc");
        assertEquals(job.getAdress(), "Carrer Bellesguard, 23-7, 08348 Cabrils, Barcelona");
        assertEquals(job.getCharge(), "charge");
        assertEquals(job.getRequirements(), "requi");
        assertEquals(job.getHoursDay(), "5");
        assertEquals(job.getHoursWeek(), "12");
        assertEquals(job.getContractDuration(), "50");
        assertEquals(job.getPlacesLimit(), "120");
        assertEquals(job.getSalary(), "1200");
        assertEquals(job.getPhoneNumber(), "2345");
        assertEquals(job.getEnded(), false);
    }

}
