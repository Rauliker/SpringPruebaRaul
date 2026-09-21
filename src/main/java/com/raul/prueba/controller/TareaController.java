package com.raul.prueba.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.model.Tarea;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @GetMapping("/{id}")
    public String detalle(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "formato", defaultValue = "corto") String formato) {

        return "Tarea " + id + " en formato " + formato;
    }
    
    @PostMapping
    public String crear() {
        return "Alguien ha hecho un POST";
    }

    @GetMapping("/ejemplo")
    public Tarea ejemplo() {
        return new Tarea(1, "Revisar el login", "alta", false);
    }

    @GetMapping
    public List<Tarea> lista() {
        return List.of(
            new Tarea(1, "Revisar el login", "alta", false),
            new Tarea(2, "Actualizar dependencias", "baja", true)
        );
    }
}
