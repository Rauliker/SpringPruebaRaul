package com.raul.prueba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.memoria.MemoriaProyecto;
import com.raul.prueba.model.Proyecto;
import com.raul.prueba.model.Tarea;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private final List<Proyecto> proyectos;
    private final List<Tarea> tareas;

    private int siguienteId = 1;

    public ProyectoController(MemoriaProyecto memoria) {
        this.proyectos = memoria.getProyectos();
        this.tareas = memoria.getTareas();
    }

    // GET /proyectos
    @GetMapping
    public List<Proyecto> lista(
            @RequestParam(name = "activo", required = false) Boolean activo) {

        if (activo == null) {
            actualizarTareasDeTodosLosProyectos();
            return proyectos;
        }

        List<Proyecto> resultado = new ArrayList<>();

        for (Proyecto proyecto : proyectos) {

            if (proyecto.isActivo() == activo
                    && proyecto.isRequired()) {

                actualizarTareas(proyecto);
                resultado.add(proyecto);
            }
        }

        return resultado;
    }

    // GET /proyectos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> detalle(
            @PathVariable(name = "id") int id) {

        for (Proyecto proyecto : proyectos) {

            if (proyecto.getId() == id) {

                actualizarTareas(proyecto);

                return ResponseEntity.ok(proyecto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET /proyectos/{id}/tareas
    @GetMapping("/{id}/tareas")
    public ResponseEntity<List<Tarea>> tareasDelProyecto(
            @PathVariable(name = "id") int id) {

        boolean existe = false;

        for (Proyecto proyecto : proyectos) {

            if (proyecto.getId() == id) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {

            if (tarea.getProyectoId() != null
                    && tarea.getProyectoId() == id) {

                resultado.add(tarea);
            }
        }

        return ResponseEntity.ok(resultado);
    }

    // POST /proyectos
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Proyecto> crear(
            @RequestBody Proyecto proyecto) {

        proyecto.setId(siguienteId);
        siguienteId++;

        proyecto.setTareas(new ArrayList<>());

        proyectos.add(proyecto);

        return ResponseEntity.ok(proyecto);
    }

    // PUT /proyectos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(
            @PathVariable(name = "id") int id,
            @RequestBody Proyecto datos) {

        for (int i = 0; i < proyectos.size(); i++) {

            if (proyectos.get(i).getId() == id) {

                datos.setId(id);

                actualizarTareas(proyectos.get(i));

                datos.setTareas(proyectos.get(i).getTareas());

                proyectos.set(i, datos);

                return ResponseEntity.ok(datos);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE /proyectos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable(name = "id") int id) {

        boolean eliminado = proyectos.removeIf(
                proyecto -> proyecto.getId() == id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    // --------------------------------------------------
    // MÉTODOS AUXILIARES
    // --------------------------------------------------

    private void actualizarTareas(Proyecto proyecto) {

        List<Tarea> tareasProyecto = new ArrayList<>();

        for (Tarea tarea : tareas) {

            if (tarea.getProyectoId() != null
                    && tarea.getProyectoId() == proyecto.getId()) {

                tareasProyecto.add(tarea);
            }
        }

        proyecto.setTareas(tareasProyecto);
    }

    private void actualizarTareasDeTodosLosProyectos() {

        for (Proyecto proyecto : proyectos) {
            actualizarTareas(proyecto);
        }
    }
}