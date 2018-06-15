package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Service;

import org.junit.Test;

public class ServiceUnity {

    private Service service = new Service(0, "lodge", "email@email.com", "service", "desc",
            "adress", "12345", false);

    @Test
    public void testGets() {
        assertEquals(service.getId(), 0);
        assertEquals(service.getName(), "service");
        assertEquals(service.getEmail(), "email@email.com");
        assertEquals(service.getAdress(), "adress");
        assertEquals(service.getPhoneNumber(), "12345");
        assertEquals(service.getEnded(), false);
    }
}
