package com.example.usuario.rekindlefrontend.data.entity.chat;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message implements Parcelable {

    @SerializedName("id")
    @Expose
    private int idMessage;
    @SerializedName("idChat")
    @Expose
    private int idChat;
    @SerializedName("owner")
    @Expose
    private User owner;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("content")
    @Expose
    private String content;

    public Message() {
    }

    public Message(int idChat, User owner, String content) {
        this.idChat = idChat;
        this.owner = owner;
        Date date = new Date();
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.FRANCE).format(
                date);
        this.content = content;
    }


    public Message(int idMessage, int idChat, User owner, String content) {
        this.idMessage = idMessage;
        this.idChat = idChat;
        this.owner = owner;
        Date date = new Date();
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.FRANCE)
                .format(date);
        this.content = content;
    }

    protected Message(Parcel in) {
        idMessage = in.readInt();
        idChat = in.readInt();
        owner = in.readParcelable(User.class.getClassLoader());
        timestamp = in.readString();
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'", Locale.FRANCE).format(
                timestamp);
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idMessage);
        dest.writeInt(idChat);
        dest.writeParcelable(owner, flags);
        dest.writeString(timestamp);
        dest.writeString(content);
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timeStamp) {
        this.timestamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + idMessage +
                ", idChat=" + idChat +
                ", owner=" + owner +
                ", timestamp='" + timestamp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
