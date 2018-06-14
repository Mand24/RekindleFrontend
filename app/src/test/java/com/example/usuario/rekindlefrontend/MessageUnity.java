package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.example.usuario.rekindlefrontend.data.entity.user.User;

import org.junit.Test;

public class MessageUnity {

    User user_voluntario = new User("Volunteer", "voluntario@mail.com", "pass123",
            "userVoluntario", "apellidoUno", "apellidoDos", "foto");

    private Message mMessage = new Message(1, 1, user_voluntario, "message content");

    @Test
    public void testsGet() {
        assertEquals(mMessage.getIdChat(), 1);
        assertEquals(mMessage.getIdMessage(), 1);
        assertEquals(mMessage.getContent(), "message content");
    }
}
