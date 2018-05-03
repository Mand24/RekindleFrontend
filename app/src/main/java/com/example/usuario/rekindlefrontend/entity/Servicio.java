package com.example.usuario.rekindlefrontend.entity;

import java.io.Serializable;

public class Servicio implements Serializable {

    private String nombre;
    private String descripcion;
    private String direccion;
    private String numero;
    private String valoracion;
    private int tipo;
    private int id;

    public Servicio(int id, String nombre, String descripcion, String direccion,
            String numero, String valoracion, int tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.numero = numero;
        this.valoracion = valoracion;
        this.tipo = tipo;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo;
    }

    public String getValoracion() {
        return valoracion;
    }
}
