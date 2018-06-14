package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.user.User;

import org.junit.Test;

public class DonationRequestUnity {

    private User user_refugee = new User ("Refugee", "refugee@mail.com", "pass123",
            "userRefugee", "apellidoUno", "apellidoDos", "foto");

    private Donation donation = new Donation (3, "voluntario@gmail.com", "donacion", "desc",
            "Carrer " + "Torrent de Can Cama, 7-5, 08348 Cabrils, Barcelona", "123", "12:00", "13:00",
            "123445", false);

    private DonationRequest donationReq = new DonationRequest(user_refugee, donation, "porque "
            + "soy un tipo generoso");

    @Test
    public void testGets() {
        assertEquals(donationReq.getDonation(), donation);
        assertEquals(donationReq.getRefugeeMail(), user_refugee.getMail());
        assertEquals(donationReq.getMotive(), "porque soy un tipo generoso");
    }
}
