package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CursoEducativo extends Servicio {
    @SerializedName("ambit")
    @Expose
    private String ambito;
    @SerializedName("requirements")
    @Expose
    private String requisitos;
    @SerializedName("schedule")
    @Expose
    private String horario;
    @SerializedName("places")
    @Expose
    private String plazas;
    @SerializedName("price")
    @Expose
    private String precio;

    public CursoEducativo (int id, String email, String nombre, String descripcion, String direccion,
            String ambito, String requisitos, String horario, String plazas, String precio,
            String numero){
        super(id, 2, email, nombre, descripcion, direccion, numero);
        this.ambito = ambito;
        this.requisitos = requisitos;
        this.horario = horario;
        this.plazas = plazas;
        this.precio = precio;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPlazas() {
        return plazas;
    }

    public void setPlazas(String plazas) {
        this.plazas = plazas;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "CursoEducativo{" +
                "ambito='" + ambito + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", horario='" + horario + '\'' +
                ", plazas='" + plazas + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
