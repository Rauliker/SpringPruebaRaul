package com.raul.prueba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raul.prueba.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> Usuarios = new ArrayList<>();

    @GetMapping
    public List<Usuario> lista() {
        return Usuarios;
    }

    @GetMapping("/{id}")
    public Usuario detalle(@PathVariable(name = "id") int id) {
        for (Usuario Usuario : Usuarios) {
            if (Usuario.getId() == id) {
                return Usuario;
            }
        }
        return null;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario Usuario) {
        Usuarios.add(Usuario);
        return Usuario;
    }
}