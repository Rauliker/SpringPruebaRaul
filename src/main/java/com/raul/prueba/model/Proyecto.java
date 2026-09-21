package com.raul.prueba.model;

public class Proyecto {
    private int id;
    private String nombre;
    private String descripcion;
    private int numeroDeIncidencias;

    public Proyecto() {
    }

    public Proyecto(int id, String nombre, String descripcion, int numeroDeIncidencias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroDeIncidencias = numeroDeIncidencias;
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

    public int isnumeroDeIncidencias() {
        return numeroDeIncidencias;
    }

    public void setnumeroDeIncidencias(int numeroDeIncidencias) {
        this.numeroDeIncidencias = numeroDeIncidencias;
    }
}

    
