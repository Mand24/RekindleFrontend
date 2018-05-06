package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donacion extends Servicio {
    @SerializedName("places")
    @Expose
    private String limiteSolicitudes;
    @SerializedName("startTime")
    @Expose
    private String horaInicio;
    @SerializedName("endTime")
    @Expose
    private String horaFin;


    public Donacion (int id, String email, String nombre, String descripcion, String direccion,
            String limiteSolicitudes,
            String horaInicio, String horaFin, String numero){
        super(id, 1, email, nombre, descripcion, direccion, numero);
        this.limiteSolicitudes = limiteSolicitudes;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getLimiteSolicitudes() {
        return limiteSolicitudes;
    }

    public void setLimiteSolicitudes(String limiteSolicitudes) {
        this.limiteSolicitudes = limiteSolicitudes;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "Donacion{" +
                "limiteSolicitudes='" + limiteSolicitudes + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
