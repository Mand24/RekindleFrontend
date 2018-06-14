package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.data.entity.link.Link;

import org.junit.Test;

public class LinkUnity {

    private Link link = new Link("link type", "http://wwww.google.com", "description");

    @Test
    public void testGets () {
        assertEquals(link.getType(), "link type");
        assertEquals(link.getDescription(), "description");
        assertEquals(link.getUrl(), "http://wwww.google.com");
    }

}
