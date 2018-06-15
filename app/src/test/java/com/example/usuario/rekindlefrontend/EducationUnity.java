package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.service.Education;

import org.junit.Test;

public class EducationUnity {

    private Education education = new Education (0, "voluntario@gmail.com",
            "education", "descr", "Passatge Passalaigua, 14, 08348 Cabrils, Barcelona",
            "ambit", "requirements", "schedule", "40", "123,2", "12", false);

    @Test
    public void testGets() {
        assertEquals(education.getId(), 0);
        assertEquals(education.getName(), "education");
        assertEquals(education.getEmail(), "voluntario@gmail.com");
        assertEquals(education.getDescription(), "descr");
        assertEquals(education.getAdress(), "Passatge Passalaigua, 14, 08348 Cabrils, Barcelona");
        assertEquals(education.getAmbit(), "ambit");
        assertEquals(education.getRequirements(), "requirements");
        assertEquals(education.getSchedule(), "schedule");
        assertEquals(education.getPlacesLimit(), "40");
        assertEquals(education.getPrice(), "123,2");
        assertEquals(education.getPhoneNumber(), "12");
        assertEquals(education.getEnded(), false);
    }
}
