package com.raul.prueba.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.model.Proyecto;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @GetMapping("/{id}/incidencias")
    public String detalleProyectoTodasIcidencias(@PathVariable(name = "id") int id) {
        return "Incidencias del proyecto " + id;
    }

    @GetMapping
    public List<Proyecto> lista() {
        return List.of(
                new Proyecto(1, "Proyecto 1", "Descripción del proyecto 1", 5),
                new Proyecto(2, "Proyecto 2", "Descripción del proyecto 2", 3));
    }

    @GetMapping("/{id}")
    public List<Proyecto> Json(@PathVariable(name = "id") int id) {
        return List.of(
                new Proyecto(id, "Proyecto 1", "Descripción del proyecto 1", (int) (Math.random() * 10) + 1));
    }
}
