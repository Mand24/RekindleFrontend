package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.misc.Report;
import com.example.usuario.rekindlefrontend.data.entity.user.User;

import org.junit.Test;

public class ReportUnity {

    private User user = new User("Volunteer", "voluntario@mail.com", "pass123",
            "userVoluntario", "apellidoUno", "apellidoDos", "foto");


    private User user_reported = new User("reportedUser", "voluntario@mail.com", "pass123",
            "userReported", "apellidoUno", "apellidoDos", "foto");

    private Report report = new Report(user, user_reported, "huele mal");

    @Test
    public void testGets() {
        assertEquals(report.getInformerUserMail(), user.getMail());
        assertEquals(report.getReportedUserMail(), user_reported.getMail());
        assertEquals(report.getMotive(), "huele mal");
    }
}
