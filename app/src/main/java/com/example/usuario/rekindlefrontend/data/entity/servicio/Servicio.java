package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Servicio implements Serializable {
    @SerializedName("tipo")
    @Expose
    private int tipo;
    @SerializedName("id")
    @Expose
    private int id;
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

    @Override
    public String toString() {
        return "Servicio{" +
                "tipo=" + tipo +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numero='" + numero + '\'' +
                ", valoracion='" + valoracion + '\'' +
                '}';
    }
}
