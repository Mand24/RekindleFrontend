package com.example.usuario.rekindlefrontend;

import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.MessagesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapterUnity {

    private List<Message> messages;
    private Context context;

    private MessagesAdapter messagesAdapter;

    @Before
    public void before() {
        messages = new ArrayList<Message>();
        Message m1 = new Message();
        Message m2 = new Message();

        messages.add(m1);
        messages.add(m2);

        messagesAdapter = new MessagesAdapter(messages, context);
    }

    @Test
    public void testConstructorr() {
        messagesAdapter = new MessagesAdapter(messages, context);

        assertNotNull(messagesAdapter);
    }

    @Test
    public void testCount() {
        int count = messagesAdapter.getItemCount();

        assertEquals("m1 + m2 = 2", count, 2);
    }

    @Test
    public void testGetSet() {
        List<Message> newLisst = new ArrayList<Message>();
        messagesAdapter.setMessages(newLisst);

        assertEquals(newLisst, messagesAdapter.getMessages());
    }

}
