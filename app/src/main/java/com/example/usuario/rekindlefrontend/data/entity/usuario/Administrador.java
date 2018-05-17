package com.example.usuario.rekindlefrontend.data.entity.usuario;

public class Administrador extends Usuario {

    public Administrador(String mail, String password, String name, String surname1,
            String surname2) {
        super("Admin", mail, password, name, surname1, surname2);
    }
}
