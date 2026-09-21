package com.raul.prueba.model;

public class Usuario {
    private int id;
    private String nombre;
    private String descripcion;
    private int edad;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String descripcion, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int isedad() {
        return edad;
    }

    public void setedad(int edad) {
        this.edad = edad;
    }
}
