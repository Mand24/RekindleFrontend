package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.ChatsAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatsAdapterUnity {

    private List<Chat> chats;
    private CustomItemClickListener listener;
    private Context context;

    private ChatsAdapter chatsAdapter;

    @Before
    public void before() {

        // init: chats
        Chat chat1 = new Chat();
        Chat chat2 = new Chat();
        chats = new ArrayList<Chat>();
        chats.add(chat1);
        chats.add(chat2);

        // init: chatsAdapter
        chatsAdapter = new ChatsAdapter (chats, listener, context);
    }

    @Test
    public void testConstructor() {
        chatsAdapter = new ChatsAdapter (chats, listener, context);
        assertNotNull (chatsAdapter);
    }

    @Test
    public void testItemCount() {
        int count = chatsAdapter.getItemCount();
        assertEquals("chat1 + chat2 = 2", count, 2);
    }

    @Test
    public void testGetSetChats()
    {
        List<Chat> newListChat = new ArrayList<Chat> ();
        chatsAdapter.setChats(newListChat);

        assertEquals(newListChat, chatsAdapter.getChats());
    }
}
