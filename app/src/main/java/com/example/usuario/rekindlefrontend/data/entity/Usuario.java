package com.example.usuario.rekindlefrontend.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    @SerializedName("mail")
    @Expose
    protected String mail;
    @SerializedName("password")
    @Expose
    protected String password;
    @SerializedName("name")
    @Expose
    protected String name;
    @SerializedName("surname1")
    @Expose
    protected String surname1;
    @SerializedName("surname2")
    @Expose
    protected String surname2;

    public Usuario(){}

    public Usuario(String mail , String password, String name, String surname1, String surname2) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
    }

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
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname1='" + surname1 + '\'' +
                ", surname2='" + surname2 + '\'' +
                '}';
    }
}
