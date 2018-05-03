package com.example.usuario.rekindlefrontend.data.entity.usuario;

public class Voluntario extends Usuario {

    public Voluntario(){
        super();
    }

    public Voluntario(String mail, String password, String name, String surname1,
            String surname2) {
        super(1, mail, password, name, surname1, surname2);
    }
}
