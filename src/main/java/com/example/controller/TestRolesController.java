package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {
    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')") // Solo puede acceder si tiene el rol de administrador (ADMIN)
    public String accessAdmin() {
        return "Hola, has accedido con el rol de administrador";
    }

    @PreAuthorize("hasRole('USER')") // Solo puede acceder si tiene el rol de usuario (USER)
    @GetMapping("/accessUser")
    public String accessUser() {
        return "Hola, has accedido con el rol de user";
    }

}
