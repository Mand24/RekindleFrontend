package com.example.usuario.rekindlefrontend.data.entity.usuario;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Parcelable {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname1")
    @Expose
    private String surname1;
    @SerializedName("surname2")
    @Expose
    private String surname2;

    public Usuario(){}

    public Usuario(String tipo, String mail, String password, String name, String surname1, String
            surname2) {
        this.tipo = tipo;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
    }


    protected Usuario(Parcel in) {
        tipo = in.readString();
        mail = in.readString();
        password = in.readString();
        name = in.readString();
        surname1 = in.readString();
        surname2 = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tipo);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(surname1);
        dest.writeString(surname2);
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tipo='" + tipo + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname1='" + surname1 + '\'' +
                ", surname2='" + surname2 + '\'' +
                '}';
    }
}
