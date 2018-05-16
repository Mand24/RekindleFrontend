package com.example.usuario.rekindlefrontend.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;

import java.util.List;

/**
 * Created by ORION on 16/05/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{

    public static final int VIEW_TYPE_MY_MESSAGE = 0;
    public static final int VIEW_TYPE_OTHER_MESSAGE = 1;

    private List<Message> messages;
    private Context mContext;

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public MessageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyMessageViewHolder extends MessageViewHolder {

        private TextView txtMyMessage, txtMyMessageTime;

        public MyMessageViewHolder(View itemView) {
            super(itemView);
            txtMyMessage = (TextView) itemView.findViewById(R.id.txtMyMessage);
            txtMyMessageTime = (TextView) itemView.findViewById(R.id.txtMyMessageTime);
        }

    }

    public class OtherMessageViewHOlder extends MessageViewHolder {

        private TextView txtOtherMessage, txtOtherMessageTime, txtOtherMessageUser;

        public OtherMessageViewHOlder(View itemView) {
            super(itemView);
            txtOtherMessage = (TextView) itemView.findViewById(R.id.txtOtherMessage);
            txtOtherMessageTime = (TextView) itemView.findViewById(R.id.txtOtherMessageTime);
            txtOtherMessageUser = (TextView) itemView.findViewById(R.id.txtOtherUser);
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


}
