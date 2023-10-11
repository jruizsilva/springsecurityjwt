package com.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    private String email;
    @NotBlank(message = "Username is required")
    @Size(max = 30)
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST,
                fetch = FetchType.EAGER,
                targetEntity = RoleEntity.class)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}