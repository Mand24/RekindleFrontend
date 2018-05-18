package com.example.usuario.rekindlefrontend.view.usuarios.chat;

import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getChannel;
import static com.example.usuario.rekindlefrontend.data.pusher.Comm.getPusher;
import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.adapters.MessagesAdapter;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowChat extends AppBaseActivity {

    private APIService mAPIService;
    private Chat chat;
    private List<Message> messages;
    private Message message;
    private MessagesAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chat);

        getSupportActionBar().setTitle(R.string.showChat);

        mAPIService = APIUtils.getAPIService();

        chat = getIntent().getParcelableExtra("Chat");

        sendGetMessagesChat();

        recyclerView = findViewById(R.id.messageList);
        mAdapter = new MessagesAdapter(messages,this);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
      
        initializeData();

        AppCompatButton btnSend = (AppCompatButton) findViewById(R.id.btnSendMessage);
        final EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                message = new Message(chat.getIdChat(),getUser(getApplicationContext()),txtMessage.getText().toString());
                sendSendMessage();
                txtMessage.getText().clear();
            }
        });

        runPusher();
    }

    public void runPusher() {

        Pusher pusher = getPusher();
        Channel channel = getChannel();

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Message message = gson.fromJson(data, Message.class);
                        mAdapter.addMessage(message);

                        /*// have the ListView scroll down to the new message
                        messagesView.setSelection(messageAdapter.getCount() - 1);*/
                    }

                });
            }
        });

        pusher.connect();
    }

    protected void initializeData() {

        mAdapter.setMessages(messages);
        mAdapter.notifyDataSetChanged();
    }

    public void sendSendMessage() {
        mAPIService.sendMessage(getUser(this).getMail(), chat.getIdChat(), message)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }

    public void sendGetMessagesChat() {

        mAPIService.getMessagesChat(getUser(this).getMail(), chat.getIdChat())
                .enqueue(new Callback<ArrayList<Message>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Message>> call,
                            Response<ArrayList<Message>>
                                    response) {
                        System.out.println("url " + call.request().url());

                        if (response.isSuccessful()) {
                            System.out.println("dentro respuesta ok");
                            messages = response.body();

                        } else {
                    /*if (response.body() != null) {
                        System.out.println("Resposta: " + response.toString
                                ());
                    } else {
                        System.out.println("messages null");
                    }
                    System.out.println("Mensaje: " + response.message());
                    System.out.println("codi: " + response.code());
                    System.out.println("dentro respuesta failed");*/
                            Toast.makeText(getApplicationContext(), getResources().getString(R
                                    .string.error), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R
                                .string.error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(ShowChat.this, Login.class);
        startActivity(i);
    }
}
