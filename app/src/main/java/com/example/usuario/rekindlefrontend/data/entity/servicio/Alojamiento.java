package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alojamiento extends Servicio {
    @SerializedName("limiteSolicitudes")
    @Expose
    private String limiteSolicitudes;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public Alojamiento (int id, String email, String nombre, String descripcion, String
            direccion, String
            limiteSolicitudes, String fecha, String numero){
        super(id, 0, email, nombre, descripcion, direccion, numero);
        this.limiteSolicitudes = limiteSolicitudes;
        this.fecha = fecha;
    }

    public String getLimiteSolicitudes() {
        return limiteSolicitudes;
    }

    public void setLimiteSolicitudes(String limiteSolicitudes) {
        this.limiteSolicitudes = limiteSolicitudes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "limiteSolicitudes='" + limiteSolicitudes + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
