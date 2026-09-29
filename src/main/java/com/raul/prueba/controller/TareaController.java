package com.raul.prueba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.memoria.MemoriaProyecto;
import com.raul.prueba.model.Proyecto;
import com.raul.prueba.model.Tarea;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final List<Tarea> tareas;
    private final List<Proyecto> proyectos;

    private int siguienteId = 1;

    public TareaController(MemoriaProyecto memoria) {
        this.tareas = memoria.getTareas();
        this.proyectos = memoria.getProyectos();
    }

    // GET /tareas
    @GetMapping
    public List<Tarea> lista(
            @RequestParam(name = "completada", required = false) Boolean completada) {

        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {

            if (completada == null
                    || tarea.isCompletada() == completada) {

                asignarProyecto(tarea);

                resultado.add(tarea);
            }
        }

        return resultado;
    }

    // GET /tareas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> detalle(
            @PathVariable(name = "id") int id) {

        for (Tarea tarea : tareas) {

            if (tarea.getId() == id) {

                asignarProyecto(tarea);

                return ResponseEntity.ok(tarea);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // POST /tareas
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        if (tarea.getProyectoId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Proyecto proyectoEncontrado = null;
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == tarea.getProyectoId()) {
                proyectoEncontrado = proyecto;
                break;
            }
        }
        if (proyectoEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        tarea.setId(siguienteId);
        siguienteId++;
        tarea.setProyecto(proyectoEncontrado);
        tareas.add(tarea);
        return ResponseEntity.ok(tarea);
    }

    // POST /tareas/espejo
    @PostMapping("/espejo")
    public Tarea espejo(@RequestBody Tarea tarea) {

        System.out.println(
                "He recibido: "
                        + tarea.getTitulo()
                        + " / "
                        + tarea.getPrioridad()
                        + " / completada="
                        + tarea.isCompletada());

        return tarea;
    }

    // PUT /tareas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(
            @PathVariable(name = "id") int id,
            @RequestBody Tarea datos) {

        for (int i = 0; i < tareas.size(); i++) {

            if (tareas.get(i).getId() == id) {

                if (datos.getProyectoId() == null) {
                    return ResponseEntity.badRequest().build();
                }

                boolean proyectoExiste = false;

                for (Proyecto proyecto : proyectos) {

                    if (proyecto.getId() == datos.getProyectoId()) {

                        proyectoExiste = true;
                        break;
                    }
                }

                if (!proyectoExiste) {
                    return ResponseEntity.notFound().build();
                }

                datos.setId(id);

                asignarProyecto(datos);

                tareas.set(i, datos);

                return ResponseEntity.ok(datos);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE /tareas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable(name = "id") int id) {

        boolean eliminado = tareas.removeIf(
                tarea -> tarea.getId() == id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // PATCH /tareas/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> modificar(
            @PathVariable(name = "id") int id,
            @RequestBody Tarea cambios) {

        for (Tarea tarea : tareas) {

            if (tarea.getId() == id) {

                if (cambios.getTitulo() != null) {
                    tarea.setTitulo(cambios.getTitulo());
                }

                if (cambios.getPrioridad() != null) {
                    tarea.setPrioridad(
                            cambios.getPrioridad());
                }

                asignarProyecto(tarea);

                return ResponseEntity.ok(tarea);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET /tareas/diagnostico
    @GetMapping("/diagnostico")
    public String diagnostico(
            @RequestHeader(name = "User-Agent") String cliente,
            @RequestHeader(name = "Accept") String acepta) {

        return "Me llama: "
                + cliente
                + "\nQuiere recibir: "
                + acepta;
    }

    // --------------------------------------------------
    // MÉTODO AUXILIAR
    // --------------------------------------------------

    private void asignarProyecto(Tarea tarea) {

        if (tarea.getProyectoId() == null) {
            return;
        }

        for (Proyecto proyecto : proyectos) {

            if (proyecto.getId() == tarea.getProyectoId()) {

                tarea.setProyecto(proyecto);
                return;
            }
        }
    }
}