package com.example.controller;

import com.example.controller.request.CreateUserDTO;
import com.example.entities.ERole;
import com.example.entities.RoleEntity;
import com.example.entities.UserEntity;
import com.example.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PrincipalController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String principal() {
        return "Hello World Not Secured";
    }

    @GetMapping("/helloSecured")
    public String principalSecured() {
        return "Hello World Secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody final CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = createUserDTO.getRoles()
                                             .stream()
                                             .map(role -> RoleEntity.builder()
                                                                    .name(ERole.valueOf(role))
                                                                    .build())
                                             .collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder()
                                          .email(createUserDTO.getEmail())
                                          .username(createUserDTO.getUsername())
                                          .password(passwordEncoder.encode(createUserDTO.getPassword()))
                                          .roles(roles)
                                          .build();

        UserEntity savedUser = userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(savedUser);

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
