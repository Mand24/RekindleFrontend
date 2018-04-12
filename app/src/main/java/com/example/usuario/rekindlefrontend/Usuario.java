package com.example.usuario.rekindlefrontend;

import java.io.Serializable;

public class Usuario implements Serializable {

    protected String mail, password, name, surname1, surname2;

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

}
