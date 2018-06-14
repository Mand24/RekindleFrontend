package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Lodge;

import org.junit.Test;

public class LodgeUnity {

    private Lodge lodge = new Lodge(1, "voluntario@gmail.com", "lodge",
            "desc", "Carrer Infern d'en Parera, 2, 08348 Cabrils, Barcelona", "12","12-03-2018",
            "1234", false);

    @Test
    public void testLodge() {
        assertEquals(lodge.getId(), 1);
        assertEquals(lodge.getName(), "lodge");
        assertEquals(lodge.getDescription(), "desc");
        assertEquals(lodge.getAdress(), "Carrer Infern d'en Parera, 2, 08348 Cabrils, Barcelona");
        assertEquals(lodge.getPlacesLimit(), "12");
        assertEquals(lodge.getDateLimit(), "12-03-2018");
        assertEquals(lodge.getPhoneNumber(), "1234");
        assertEquals(lodge.getEnded(), false);
    }
}
