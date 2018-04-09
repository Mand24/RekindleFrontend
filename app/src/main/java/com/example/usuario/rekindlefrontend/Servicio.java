package com.example.usuario.rekindlefrontend;

public class Servicio {

    private String nombre, descripcion, direccion, fecha, numero, valoracion;
    private int tipo, id;

    Servicio(int id , String nombre, String descripcion, String direccion, String fecha, String numero, String valoracion, int tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.fecha = fecha;
        this.numero = numero;
        this.valoracion = valoracion;
        this.tipo = tipo;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNumero() {
        return numero;
    }

    public int getTipo() {
        return tipo;
    }

    public String getValoracion() {
        return valoracion;
    }
}
