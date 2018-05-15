package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alojamiento extends Servicio {
    @SerializedName("places")
    @Expose
    private String limiteSolicitudes;
    @SerializedName("dateLimit")
    @Expose
    private String fecha;

    public Alojamiento (int id, String email, String nombre, String descripcion, String
            direccion, String
            limiteSolicitudes, String fecha, String numero){
        super(id, "Lodge", email, nombre, descripcion, direccion, numero);
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
                "id=" + getId() +
                ", tipo=" + getTipo() +
                ", volunteer='" + getEmail() + '\'' +
                ", name='" + getNombre() + '\'' +
                ", description='" + getDescripcion() + '\'' +
                ", adress='" + getDireccion() + '\'' +
                ", phoneNumber='" + getNumero() + '\'' +
                ", limiteSolicitudes='" + limiteSolicitudes + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
