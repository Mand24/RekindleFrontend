package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;

import org.junit.Test;

public class RefugeeUnity {

    private Refugee refugee = new Refugee("refugiado@gmail.com", "pass1234", "myname",
            "refugiadoSurname", "refugadioSecondSurname", "photo", "123456789", "12-09-2018",
            "Male",
            "Barcelona", "cabrils", "ethinc", "A+", "brown", "my life");

    @Test
    public void testGets() {
        assertEquals(refugee.getMail(), "refugiado@gmail.com");
        assertEquals(refugee.getPassword(), "pass1234");
        assertEquals(refugee.getName(), "myname");
        assertEquals(refugee.getSurname1(), "refugiadoSurname");
        assertEquals(refugee.getSurname2(), "refugadioSecondSurname");
        assertEquals(refugee.getPhoto(), "photo");
        assertEquals(refugee.getPhoneNumber(), "123456789");
        assertEquals(refugee.getBirthDate(), "12-09-2018");
        assertEquals(refugee.getSex(), "Male");
        assertEquals(refugee.getCountry(), "Barcelona");
        assertEquals(refugee.getTown(), "cabrils");
        assertEquals(refugee.getEthnic(), "ethinc");
        assertEquals(refugee.getBloodType(), "A+");
        assertEquals(refugee.getEyeColor(), "brown");
        assertEquals(refugee.getBiography(), "my life");
    }
}
