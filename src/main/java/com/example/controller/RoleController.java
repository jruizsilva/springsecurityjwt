package com.example.controller;

import com.example.entities.RoleEntity;
import com.example.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

}
