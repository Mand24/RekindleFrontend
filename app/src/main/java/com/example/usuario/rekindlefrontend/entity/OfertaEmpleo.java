package com.example.usuario.rekindlefrontend.entity;

public class OfertaEmpleo extends Servicio {

    public String puesto;
    public String requisitos;
    public String jornada;
    public String horasSemana;
    public String duracion;
    public String plazasDisponibles;
    public String sueldo;

    public OfertaEmpleo (int id, String nombre, String descripcion, String direccion, String puesto,
            String requisitos, String jornada, String horasSemana, String duracion, String
            plazasDisponibles, String sueldo, String numero, String valoracion, int tipo){
        super(id, nombre, descripcion, direccion, numero, valoracion, tipo);
        this.puesto = puesto;
        this.requisitos = requisitos;
        this.jornada = jornada;
        this.horasSemana = horasSemana;
        this.duracion = duracion;
        this.plazasDisponibles = plazasDisponibles;
        this.sueldo = sueldo;
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

    public String getDuracion() { return duracion; }

    public String getHorasSemana() { return horasSemana; }

    public String getJornada() { return jornada; }

    public String getPlazasDisponibles() { return plazasDisponibles; }

    public String getPuesto() { return puesto; }

    public String getRequisitos() { return requisitos; }

    public String getSueldo() { return sueldo; }

}
