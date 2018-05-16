package com.example.usuario.rekindlefrontend.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Message;

import java.util.List;

/**
 * Created by ORION on 16/05/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    public static final int VIEW_TYPE_MY_MESSAGE = 0;
    public static final int VIEW_TYPE_OTHER_MESSAGE = 1;

    private List<Message> messages;
    private Context mContext;

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public MessageViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Message message){}
    }

    public class MyMessageViewHolder extends MessageViewHolder {

        private TextView txtMyMessage, txtMyMessageTime;

        public MyMessageViewHolder(View itemView) {
            super(itemView);
            txtMyMessage = (TextView) itemView.findViewById(R.id.txtMyMessage);
            txtMyMessageTime = (TextView) itemView.findViewById(R.id.txtMyMessageTime);
        }

        @Override
        public void bind(Message message){
            txtMyMessage.setText(message.getContent());
            txtMyMessageTime.setText(message.getTimeStamp().toString());
        }
    }

    public class OtherMessageViewHolder extends MessageViewHolder {

        private TextView txtOtherMessage, txtOtherMessageTime, txtOtherMessageUser;

        public OtherMessageViewHolder(View itemView) {
            super(itemView);
            txtOtherMessage = (TextView) itemView.findViewById(R.id.txtOtherMessage);
            txtOtherMessageTime = (TextView) itemView.findViewById(R.id.txtOtherMessageTime);
            txtOtherMessageUser = (TextView) itemView.findViewById(R.id.txtOtherUser);
        }

        @Override
        public void bind(Message message){
            txtOtherMessage.setText(message.getContent());
            txtOtherMessageTime.setText(message.getTimeStamp().toString());
            txtOtherMessageUser.setText(message.getOwner().getName());
        }
    }

    public MessagesAdapter(
            List<Message> messages, Context context) {
        this.messages = messages;
        mContext = context;
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
            int i) {
        if(i == VIEW_TYPE_MY_MESSAGE) {
            return new MyMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R
                    .layout.my_message, viewGroup, false));
        } else {
            return new OtherMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R
                    .layout.other_message, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);

        holder.bind(message);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


}
