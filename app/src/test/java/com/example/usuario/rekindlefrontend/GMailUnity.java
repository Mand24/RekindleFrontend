package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.utils.GMail;

import org.junit.Test;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailUnity {

    private GMail gmail = new GMail("aleix@gmail.com", "aleixpass", "jofre@gmail.com", "subject",
            "body");

    private MimeMessage mMimeMessage;

    @Test
    public void testCreateEmailMessage() {
        try {
            InternetAddress ia = new InternetAddress("aleix@gmail.com",
                    "aleix@gmail.com");
            mMimeMessage = gmail.createEmailMessage();

            assertEquals(mMimeMessage.getSubject(), "subject");
            assertEquals(mMimeMessage.getContent(), "body");
        } catch (Exception e)
        {
        }
    }
}
