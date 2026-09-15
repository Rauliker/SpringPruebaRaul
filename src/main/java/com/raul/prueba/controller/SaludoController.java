package com.raul.prueba.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.raul.prueba.util.SpaceValidator;
import com.raul.prueba.util.StringValidator;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo(@RequestParam(name = "nombre", defaultValue = "Mundo") String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST
            );
        }
        if (!StringValidator.tieneMayuscula(nombre)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST
            );
        }
        if (!SpaceValidator.tieneEspacios(nombre)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST
            );
        }
        return "Hola, " + nombre + ".";
    }

    @GetMapping("/incidencias")
    public String buscar(
            @RequestParam(name = "estado", defaultValue = "todas") String estado,
            @RequestParam(name = "pagina", defaultValue = "1") int pagina) {

            if ((estado == "todas"|| estado == "abiertas" || estado == "cerradas") && pagina < 1) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
                );
            }

        return "Buscando incidencias con estado " + estado + ", página " + pagina;
    }
    @GetMapping("/informes")
    public String informes(
            @RequestParam(name = "desde") LocalDate desde,
            @RequestParam(name = "activo", defaultValue = "true") boolean activo) {

        return "Desde " + desde + " (día " + desde.getDayOfMonth()
                + " del mes " + desde.getMonthValue() + "), activo=" + activo;
    }
}
