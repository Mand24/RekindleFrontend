package com.example.usuario.rekindlefrontend.data.entity.usuario;

import com.example.usuario.rekindlefrontend.data.entity.user.User;

public class Administrador extends User {

    public Administrador(String mail, String password, String name, String surname1,
            String surname2, String photo) {
        super("Admin", mail, password, name, surname1, surname2, photo);
    }
}
