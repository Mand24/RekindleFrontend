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

    public Alojamiento (int id, String nombre, String descripcion, String direccion, String
            limiteSolicitudes, String fecha, String numero, String valoracion, int tipo){
        super(id, nombre, descripcion, direccion, numero, valoracion, 0);
        this.limiteSolicitudes = limiteSolicitudes;
        this.fecha = fecha;
    }

    public int getId(){ return super.getId(); }

    public String getNombre() {
        return super.getNombre();
    }

    public String getDescripcion(){
        return super.getDescripcion();
    }

    public String getDireccion() {
        return super.getDireccion();
    }

    public String getNumero() {
        return super.getNumero();
    }

    public int getTipo() {
        return super.getTipo();
    }

    public String getValoracion() {
        return super.getValoracion();
    }

    public String getLimiteSolicitudes() {
        return limiteSolicitudes;
    }

    public String getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "limiteSolicitudes='" + limiteSolicitudes + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
