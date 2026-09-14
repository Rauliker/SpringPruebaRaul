package com.raul.prueba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/manolo")
    public String hola() {
        return "Hola Manolo. ¿Cómo estás?";
    }
    @GetMapping("/clientes")
    public String estado() {
        return "Estos son los clientes que tenemos en la base de datos:";
    }

    @GetMapping("/productos/resumen")
    public String resumen() {
        return "Esta aplicación gestionará los productos de material";
    }
    @GetMapping("/anyo")
    public int anyo() {
        return 2026;
    }
}