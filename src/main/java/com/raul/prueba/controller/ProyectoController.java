package com.raul.prueba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    @GetMapping
    public String listaTodosProyectos(@RequestParam(name = "rol" ,required = false) String rol) {
        if (rol == null || rol.trim().isEmpty()) {
            
        return "Lista de proyectos";
        }
        return "Lista de proyectos con estado " + rol;
    }

    @GetMapping("/{id}")
    public String detalleProyecto(@PathVariable(name = "id") int id) {
        return "Ficha del proyecto " + id;
    }

    @GetMapping("/{id}/incidencias")
    public String detalleProyectoTodasIcidencias(@PathVariable(name = "id") int id) {
        return "Incidencias del proyecto " + id;
    }
    
}
