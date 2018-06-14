package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;

import org.junit.Test;

public class VolunteerUnity {

    private Volunteer volunteer = new Volunteer("volunteer@mail.com", "pass123", "name",
            "surnameOne", "surnameTwo","photo");

    @Test
    public void testGets() {
        assertEquals(volunteer.getMail(), "volunteer@mail.com");
        assertEquals(volunteer.getPassword(), "pass123");
        assertEquals(volunteer.getName(), "name");
        assertEquals(volunteer.getSurname1(), "surnameOne");
        assertEquals(volunteer.getSurname2(), "surnameTwo");
        assertEquals(volunteer.getPhoto(), "photo");
    }
}
