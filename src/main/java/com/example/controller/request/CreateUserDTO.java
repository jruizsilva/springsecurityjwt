package com.example.controller.request;

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
public class CreateUserDTO {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    private String email;
    @NotBlank(message = "Username is required")
    @Size(max = 30)
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    private Set<String> roles;
}
