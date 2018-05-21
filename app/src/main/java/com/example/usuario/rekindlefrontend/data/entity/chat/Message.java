package com.example.usuario.rekindlefrontend.data.entity.chat;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message implements Parcelable {

    @SerializedName("id")
    @Expose
    private int idMessage;

    @SerializedName("idChat")
    @Expose
    private int idChat;

    @SerializedName("owner")
    @Expose
    private Usuario owner;

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    @SerializedName("content")
    @Expose
    private String content;

    public Message() {
    }

    public Message(int idChat, Usuario owner, String content) {
        this.idChat = idChat;
        this.owner = owner;
        Date date = new Date();
        this.timeStamp = "2018-05-24";
        this.content = content;
    }

    public Message(int idMessage, int idChat, Usuario owner, String content) {
        this.idMessage = idMessage;
        this.idChat = idChat;
        this.owner = owner;
        this.timeStamp = "2018-05-24";
        this.content = content;
    }


    protected Message(Parcel in) {
        idMessage = in.readInt();
        idChat = in.readInt();
        owner = in.readParcelable(Usuario.class.getClassLoader());
        timeStamp = in.readString();
        content = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idMessage);
        dest.writeInt(idChat);
        dest.writeParcelable(owner, flags);
        dest.writeString(timeStamp);
        dest.writeString(content);
    }

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

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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
                ", timeStamp='" + timeStamp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
