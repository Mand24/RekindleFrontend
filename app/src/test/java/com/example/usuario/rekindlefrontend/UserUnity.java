package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.user.User;

import org.junit.Test;

public class UserUnity {


    private User user = new User("refugee", "mail@mail.com", "pass", "name", "surname1",
            "surname2", "photo");

    @Test
    public void testGets() {
        assertEquals(user.getUserType(), "refugee");
        assertEquals(user.getMail(), "mail@mail.com");
        assertEquals(user.getName(), "name");
        assertEquals(user.getPassword(), "pass");
        assertEquals(user.getSurname1(), "surname1");
        assertEquals(user.getSurname2(), "surname2");
        assertEquals(user.getPhoto(), "photo");
    }

}
