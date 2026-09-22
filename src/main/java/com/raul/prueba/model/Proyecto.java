package com.raul.prueba.model;

public class Proyecto {
    private int id;
    private String nombre;
    private String descripcion;
    private int numeroDeIncidencias;
    private boolean activo;
    private boolean required;

    public Proyecto() {
    }

    public Proyecto(int id, String nombre, String descripcion, int numeroDeIncidencias, boolean activo,
            boolean required) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroDeIncidencias = numeroDeIncidencias;
        this.activo = activo;
        this.required = required;
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

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
