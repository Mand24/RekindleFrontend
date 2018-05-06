package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Servicio implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tipo")
    @Expose
    private int tipo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("numero")
    @Expose
    private String numero;
    @SerializedName("valoracion")
    @Expose
    private String valoracion;


    public Servicio(int id, int tipo, String email, String nombre, String descripcion, String
            direccion, String numero, String valoracion) {
        this.id = id;
        this.tipo = tipo;
        this.email = email;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.numero = numero;
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numero='" + numero + '\'' +
                ", valoracion='" + valoracion + '\'' +
                '}';
    }
}
