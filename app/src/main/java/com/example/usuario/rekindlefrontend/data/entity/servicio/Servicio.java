package com.example.usuario.rekindlefrontend.data.entity.servicio;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Servicio implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("serviceType")
    @Expose
    private String tipo;
    @SerializedName("volunteer")
    @Expose
    private String volunteer;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    private int image;



    public Servicio(int id, String tipo, String email, String nombre, String descripcion, String
            direccion, String numero) {
        this.id = id;
        this.tipo = tipo;
        this.volunteer = email;
        this.name = nombre;
        this.description = descripcion;
        this.adress = direccion;
        this.phoneNumber = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return volunteer;
    }

    public void setEmail(String email) {
        this.volunteer = email;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public String getDireccion() {
        return adress;
    }

    public void setDireccion(String direccion) {
        this.adress = direccion;
    }

    public String getNumero() {
        return phoneNumber;
    }

    public void setNumero(String numero) {
        this.phoneNumber = numero;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", volunteer='" + volunteer + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image=" + image +
                '}';
    }
}
