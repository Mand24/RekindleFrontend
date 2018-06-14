package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Donation;

import org.junit.Test;

public class DonationUnity {

    private Donation donation = new Donation(3, "voluntario@gmail.com", "donacion", "desc",
            "Carrer Torrent de Can Cama, 7-5, 08348 Cabrils, Barcelona", "123", "12:00", "13:00",
            "123445", false);

    @Test
    public void testGets() {
        assertEquals(donation.getId(), 3);
        assertEquals(donation.getName(), "donacion");
        assertEquals(donation.getEmail(), "voluntario@gmail.com");
        assertEquals(donation.getDescription(), "desc");
        assertEquals(donation.getAdress(), "Carrer Torrent de Can Cama, 7-5, 08348 Cabrils, Barcelona");
        assertEquals(donation.getPlacesLimit(), "123");
        assertEquals(donation.getStartTime(), "12:00");
        assertEquals(donation.getEndTime(), "13:00");
        assertEquals(donation.getPhoneNumber(), "123445");
        assertEquals(donation.getEnded(), false);
    }
}
