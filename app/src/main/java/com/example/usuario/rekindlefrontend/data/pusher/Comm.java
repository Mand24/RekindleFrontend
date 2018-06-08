package com.example.usuario.rekindlefrontend.data.pusher;

/**
 * Created by ORION on 16/05/2018.
 */

import static android.content.Context.NOTIFICATION_SERVICE;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.view.chat.ListChats;
import com.example.usuario.rekindlefrontend.view.services.list.MyServicesRefugee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Quota;

public class Comm {

    public static final String PusherApiKey = "743a4fb4a1370f0ca9a4";
    public static final String PusherCluster = "eu";
    private static Pusher pusher;
    private static Channel channelUser;
    private static HashMap<Integer, Channel> channelsChat = new HashMap<>();
    private static HashMap<Integer, Channel> channelsService = new HashMap<>();

    /*public static void setUpPusher() {

        PusherOptions options = new PusherOptions();
        options.setCluster(PusherCluster);
        pusher = new Pusher(PusherApiKey, options);

        channel = pusher.subscribe("my-channel");

    }*/

    public static void setUpPusher() {
        PusherOptions options = new PusherOptions();
        options.setCluster(PusherCluster);
        pusher = new Pusher(PusherApiKey, options);
    }

    public static void setUpChannelUser(Activity act){
        channelUser = pusher.subscribe(getUser(act
                .getApplicationContext()).getMail());
    }

    public static void setUpChannelsChats(Activity act, ArrayList<Chat> chats) {
        for (Chat chat:chats) {
            Channel channel = pusher.subscribe(Integer.toString(chat.getIdChat()));
            channelsChat.put(chat.getIdChat(), channel);
        }
    }

    public static void setUpChannelsServices(ArrayList<Service> services) {
        for (Service service:services) {
            Channel channel = pusher.subscribe(service.getServiceType() + Integer.toString(service.getId()));
            channelsService.put(service.getId(), channel);
        }
    }

    public static void setAllChannelsNotificationsChats(final Activity act){
        for (Map.Entry<Integer, Channel> entry : channelsChat.entrySet()) {
            Channel channel = entry.getValue();

            channel.bind("new-message", new SubscriptionEventListener() {
                @Override
                public void onEvent(String channelName, String eventName, final String data) {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Comm.setNotificationMessage(act, data);
                        }
                    });


                }
            });

        }
    }

    public static void setChannelNotificationChat(final Activity act, int idChat){
        Channel channel = channelsChat.get(idChat);
        channel.bind("new-message", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Comm.setNotificationMessage(act, data);
                    }
                });
            }
        });
    }

    public static void setAllChannelsNotificationsServices(final Activity act){
        for (Map.Entry<Integer, Channel> entry : channelsService.entrySet()) {
            Channel channel = entry.getValue();

            channel.bind("updated-service", new SubscriptionEventListener() {
                @Override
                public void onEvent(String channelName, String eventName, final String data) {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Comm.setNotificationService(act, data, "updated-service");
                        }
                    });


                }
            });

            channel.bind("deleted-service", new SubscriptionEventListener() {
                @Override
                public void onEvent(String channelName, String eventName, final String data) {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Comm.setNotificationService(act, data, "deleted-service");
                        }
                    });


                }
            });

        }
    }

    public static void setChannelNotificationService(final Activity act, int idService){
        Channel channel = channelsService.get(idService);
        channel.bind("updated-service", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Comm.setNotificationService(act, data,"updated-service");
                    }
                });
            }
        });

        channel.bind("deleted-service", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Comm.setNotificationService(act, data, "deleted-service");
                    }
                });


            }
        });
    }

    public static void setChannelUserChat(final Activity act){
        channelUser.bind("new-chat", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type mapType = new TypeToken<Map<String, Integer>>() {
                        }.getType();
                        Map<String, Integer> map = gson.fromJson(data, mapType);
                        int idChat = map.get("message");
                        Channel channel = pusher.subscribe(Integer.toString(idChat));
                        channelsChat.put(idChat, channel);
                        Comm.setChannelNotificationChat(act, idChat);
                    }
                });
            }
        });
    }

    public static void setChannelUserService(final Activity act){
        channelUser.bind("enroll-service", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type mapType = new TypeToken<Map<String, Integer>>() {
                        }.getType();
                        Map<String, Service> map = gson.fromJson(data, mapType);
                        Service service = map.get("message");
                        Channel channel = pusher.subscribe(service.getServiceType() + Integer.toString(service.getId()));
                        channelsService.put(service.getId(), channel);
                        Comm.setChannelNotificationService(act, service.getId());
                    }
                });
            }
        });

        channelUser.bind("unenroll-service", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type mapType = new TypeToken<Map<String, Integer>>() {
                        }.getType();
                        Map<String, Service> map = gson.fromJson(data, mapType);
                        Service service = map.get("message");
                        pusher.unsubscribe(service.getServiceType() + Integer.toString(service.getId()));
                        channelsService.remove(service.getId());
                    }
                });
            }
        });
    }

    public static void setNotificationMessage(Activity act, String data){

        ActivityManager activityManager = (ActivityManager)act.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = activityManager.getRunningTasks(1).get(0).topActivity;

        System.out.println("nombre " + cn.getClassName());
        System.out.println("boolean " + cn.getClassName().contains("ShowChat"));
        if (!cn.getClassName().contains("ShowChat")){
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Message>>() {
            }.getType();
            Map<String, Message> map = gson.fromJson(data, mapType);
            Message message = map.get("message");
            User owner = message.getOwner();
            if (!getUser(act.getApplicationContext()).getMail().equals(owner.getMail())) {

                Intent intent = new Intent(act.getApplicationContext(), ListChats.class);

                // Create a PendingIntent; we're only using one PendingIntent (ID = 0):
                final int pendingIntentId = 0;
                PendingIntent contentIntent =
                        PendingIntent.getActivity(act.getApplicationContext(),
                                pendingIntentId, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                // Instantiate the builder and set notification elements:

                Notification notification = new Notification.Builder(
                        act.getApplicationContext())
                        .setCategory(Notification.CATEGORY_PROMO)
                        .setContentTitle(owner.getName() + " " + owner.getSurname1())
                        .setContentText(message.getContent())
                        .setSmallIcon(R.drawable.logo_r)
                        .setAutoCancel(true)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .addAction(android.R.drawable.ic_menu_view, "View details",
                                contentIntent)
                        .setContentIntent(contentIntent)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();


                // Get the notification manager:
                NotificationManager notificationManager =
                        (NotificationManager) act.getApplicationContext().getSystemService(
                                NOTIFICATION_SERVICE);

                // Publish the notification:
                final int notificationId = 0;
                notificationManager.notify(notificationId, notification);
            }
        }

    }

    //Nomes seran notificacions pels refugees que son els qui han de ser informats!!!
    public static void setNotificationService(Activity act, String data, String eventType){

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Service>>() {
        }.getType();
        Map<String, Service> map = gson.fromJson(data, mapType);
        Service service = map.get("message");

        Intent intent = new Intent(act.getApplicationContext(), MyServicesRefugee.class);

        // Create a PendingIntent; we're only using one PendingIntent (ID = 0):
        final int pendingIntentId = 0;
        PendingIntent contentIntent =
                PendingIntent.getActivity(act.getApplicationContext(),
                        pendingIntentId, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Instantiate the builder and set notification elements:
        Notification notification;
        Notification.Builder builder = new Notification.Builder(act.getApplicationContext());
        builder.setCategory(Notification.CATEGORY_PROMO);
        builder.setContentTitle(service.getName());
        builder.setSmallIcon(R.drawable.logo_r);
        builder.setAutoCancel(true);
        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
        builder.addAction(android.R.drawable.ic_menu_view, "View details",
                        contentIntent);
        builder.setContentIntent(contentIntent);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();

        if (eventType.equals("updated-service")){
            builder.setContentText(act.getResources().getString(R
                    .string.descriptionEventUpdatedService));
        }
        else {
            builder.setContentText(act.getResources().getString(R
                            .string.descriptionEventDeletedService));
        }
        notification = builder.build();

        // Get the notification manager:
        NotificationManager notificationManager =
                (NotificationManager) act.getApplicationContext().getSystemService(
                        NOTIFICATION_SERVICE);

        // Publish the notification:
        final int notificationId = 0;
        notificationManager.notify(notificationId, notification);

    }

    public static void connectPusher(){
        pusher.connect();
    }

    public static void disconnectPusher(){
        pusher.disconnect();
    }

    public static Channel getChannel(int idChat) {
        return channelsChat.get(idChat);
    }

    public static Pusher getPusher() {
        return pusher;
    }

    public static void setPusher(Pusher pusher) {
        Comm.pusher = pusher;
    }




    /*public static Channel getChannel() {
        System.out.println("getchannel: " + channel.getName());
        return channel;
    }

    public static void setChannel(Channel channel) {
        Comm.channel = channel;
    }*/
}
