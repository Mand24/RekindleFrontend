package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CursoEducativo extends Servicio {
    @SerializedName("ambito")
    @Expose
    private String ambito;
    @SerializedName("requisitos")
    @Expose
    private String requisitos;
    @SerializedName("horario")
    @Expose
    private String horario;
    @SerializedName("plazas")
    @Expose
    private String plazas;
    @SerializedName("precio")
    @Expose
    private String precio;

    public CursoEducativo (int id, String nombre, String descripcion, String direccion, String fecha,
            String ambito, String requisitos, String horario, String plazas, String precio,
            String numero, String valoracion, int tipo){
        super(id, nombre, descripcion, direccion, numero, valoracion, 2);
        this.ambito = ambito;
        this.requisitos = requisitos;
        this.horario = horario;
        this.plazas = plazas;
        this.precio = precio;
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

    public String getAmbito() { return ambito; }

    public String getRequisitos() { return requisitos; }

    public String getHorario() { return horario; }

    public String getPlazas() { return plazas; }

    public String getPrecio() { return precio; }

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
