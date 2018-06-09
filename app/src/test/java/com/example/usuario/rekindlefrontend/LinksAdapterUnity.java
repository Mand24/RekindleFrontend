package com.example.usuario.rekindlefrontend;


import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.example.usuario.rekindlefrontend.adapters.LinksAdapter;
import com.example.usuario.rekindlefrontend.data.entity.link.Link;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinksAdapterUnity {

    private List<Link> links;
    private Context context;
    private CustomItemClickListener listener;

    private LinksAdapter linksAdapter;

    @Before
    public void before() {

        // init: links
        Link link1 = new Link();
        Link link2 = new Link();
        links = new ArrayList<Link>();
        links.add(link1);
        links.add(link2);

        // init: chatsAdapter
        linksAdapter = new LinksAdapter(context, links, listener);
    }

    @Test
    public void testConstructor() {
        linksAdapter = new LinksAdapter(context, links, listener);
        assertNotNull(linksAdapter);
    }

    @Test
    public void testCount() {
        int count = linksAdapter.getItemCount();
        assertEquals("link1 + link2 = 2", count, 2);
    }

    @Test
    public void testGetSet() {
        List<Link> newLinks = new ArrayList<Link>();
        linksAdapter.setLinks(newLinks);
        assertEquals(newLinks, linksAdapter.getLinks());
    }

}