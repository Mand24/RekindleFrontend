package com.example.usuario.rekindlefrontend.data.entity.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfertaEmpleo extends Servicio {
    @SerializedName("charge")
    @Expose
    public String puesto;
    @SerializedName("requirements")
    @Expose
    public String requisitos;
    @SerializedName("hoursDay")
    @Expose
    public String jornada;
    @SerializedName("hoursWeek")
    @Expose
    public String horasSemana;
    @SerializedName("contractDuration")
    @Expose
    public String duracion;
    @SerializedName("places")
    @Expose
    public String plazasDisponibles;
    @SerializedName("salary")
    @Expose
    public String sueldo;

    public OfertaEmpleo (int id, String email, String nombre, String descripcion, String direccion, String puesto,
            String requisitos, String jornada, String horasSemana, String duracion, String
            plazasDisponibles, String sueldo, String numero){
        super(id, 3, email, nombre, descripcion, direccion, numero);
        this.puesto = puesto;
        this.requisitos = requisitos;
        this.jornada = jornada;
        this.horasSemana = horasSemana;
        this.duracion = duracion;
        this.plazasDisponibles = plazasDisponibles;
        this.sueldo = sueldo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(String horasSemana) {
        this.horasSemana = horasSemana;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(String plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "OfertaEmpleo{" +
                "puesto='" + puesto + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", jornada='" + jornada + '\'' +
                ", horasSemana='" + horasSemana + '\'' +
                ", duracion='" + duracion + '\'' +
                ", plazasDisponibles='" + plazasDisponibles + '\'' +
                ", sueldo='" + sueldo + '\'' +
                '}';
    }
}
