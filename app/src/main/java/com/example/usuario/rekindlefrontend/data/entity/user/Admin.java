package com.example.usuario.rekindlefrontend.data.entity.user;

public class Admin extends User {

    public Admin(String mail, String password, String name, String surname1,
            String surname2, String photo) {
        super("Admin", mail, password, name, surname1, surname2, photo);
    }
}
