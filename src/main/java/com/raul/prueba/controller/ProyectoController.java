package com.raul.prueba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.model.Proyecto;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private final List<Proyecto> proyectos = new ArrayList<>();

    @GetMapping
    public List<Proyecto> lista() {
        return proyectos;
    }

    @GetMapping("/{id}")
    public Proyecto detalle(@PathVariable(name = "id") int id) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                return proyecto;
            }
        }
        return null;
    }

    @PostMapping
    public Proyecto crear(@RequestBody Proyecto proyecto) {
        proyectos.add(proyecto);
        return proyecto;
    }
}
