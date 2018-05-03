package com.example.usuario.rekindlefrontend.entity;

public class CursoEducativo extends Servicio {

    private String ambito;
    private String requisitos;
    private String horario;
    private String plazas;
    private String precio;

    public CursoEducativo (int id, String nombre, String descripcion, String direccion, String fecha,
            String ambito, String requisitos, String horario, String plazas, String precio,
            String numero, String valoracion, int tipo){
        super(id, nombre, descripcion, direccion, numero, valoracion, tipo);
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
}
