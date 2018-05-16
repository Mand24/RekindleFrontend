package com.example.usuario.rekindlefrontend.data.entity.chat;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chat implements Parcelable{

    @SerializedName("idChat")
    @Expose

    private int idChat;

    @SerializedName("user1")
    @Expose
    private Usuario user1;

    @SerializedName("user2")
    @Expose
    private Usuario user2;

    public Chat(){}

    public Chat(Usuario user1, Usuario user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    protected Chat(Parcel in) {
        idChat = in.readInt();
        user1 = in.readParcelable(Usuario.class.getClassLoader());
        user2 = in.readParcelable(Usuario.class.getClassLoader());
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idChat);
        dest.writeParcelable(user1, flags);
        dest.writeParcelable(user2, flags);
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public Usuario getUser1() {
        return user1;
    }

    public void setUser1(Usuario user1) {
        this.user1 = user1;
    }

    public Usuario getUser2() {
        return user2;
    }

    public void setUser2(Usuario user2) {
        this.user2 = user2;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "idChat=" + idChat +
                ", user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }
}
