package com.example.usuario.rekindlefrontend.data.pusher;

/**
 * Created by ORION on 16/05/2018.
 */

import com.example.usuario.rekindlefrontend.adapters.MessagesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.util.List;

public class Comm {

    public static final String PusherApiKey = "743a4fb4a1370f0ca9a4";
    public static final String PusherCluster = "eu";
    private static Pusher pusher;
    private static Channel channel;

    public static void setUpPusher() {

        PusherOptions options = new PusherOptions();
        options.setCluster(PusherCluster);
        pusher = new Pusher(PusherApiKey, options);

        channel = pusher.subscribe("my-channel");

    }

    public static Pusher getPusher() {
        return pusher;
    }

    public static void setPusher(Pusher pusher) {
        Comm.pusher = pusher;
    }

    public static Channel getChannel() {
        System.out.println("getchannel: " + channel.getName());
        return channel;
    }

    public static void setChannel(Channel channel) {
        Comm.channel = channel;
    }
}
