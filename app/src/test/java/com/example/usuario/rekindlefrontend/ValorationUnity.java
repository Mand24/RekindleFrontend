package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Valoration;

import org.junit.Test;

public class ValorationUnity {

    private Float rating = new Float(2.2);
    private Valoration valoration = new Valoration (1, "lodge", "refugee@email.com", rating);

    @Test
    public void testGets() {
        assertEquals(valoration.getIdService(), 1);
        assertEquals(valoration.getServiceType(), "lodge");
        assertEquals(valoration.getMailRefugee(), "refugee@email.com");
        assertEquals(valoration.getPoints(), rating);
    }
}
