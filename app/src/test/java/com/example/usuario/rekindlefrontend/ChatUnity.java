package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.user.User;

import org.junit.Test;

public class ChatUnity {

    private User user_voluntario1 = new User ("Volunteer1", "voluntario1@mail.com", "pass123",
            "userVoluntario", "apellidoUno", "apellidoDos", "foto");

    private User user_voluntario2 = new User ("Volunteer2", "voluntario2@mail.com", "pass123",
            "userVoluntario", "apellidoUno", "apellidoDos", "foto");

    private Chat chat = new Chat(1, user_voluntario1, user_voluntario2);

    @Test
    public void testGets() {
        assertEquals(chat.getIdChat(), 1);
        assertEquals(chat.getUser1(), user_voluntario1);
        assertEquals(chat.getUser2(), user_voluntario2);
    }
}
