package com.example.usuario.rekindlefrontend.data.entity.chat;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message implements Parcelable{
    @SerializedName("idMessage")
    @Expose
    private int idMessage;

    @SerializedName("owner")
    @Expose
    private Usuario owner;

    @SerializedName("timeStamp")
    @Expose
    private Date timeStamp;

    @SerializedName("content")
    @Expose
    private String content;

    public Message(){}

    public Message(Usuario owner, String content) {
        this.owner = owner;
        this.timeStamp = new Date();
        this.content = content;
    }

    protected Message(Parcel in) {
        idMessage = in.readInt();
        owner = in.readParcelable(Usuario.class.getClassLoader());
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
        dest.writeParcelable(owner, flags);
        dest.writeString(content);
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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
                "idMessage=" + idMessage +
                ", owner=" + owner +
                ", timeStamp=" + timeStamp +
                ", content='" + content + '\'' +
                '}';
    }
}
