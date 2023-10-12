package com.example;

import com.example.entities.ERole;
import com.example.entities.RoleEntity;
import com.example.entities.UserEntity;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringsecurityjwtApplication {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityjwtApplication.class,
                              args);
    }

    @Bean
    CommandLineRunner init() {
        return (args) -> {
            UserEntity userEntity = UserEntity.builder()
                                              .email("jonathan@gmail.com")
                                              .username("jon")
                                              .password(passwordEncoder.encode("admin"))
                                              .roles(Set.of(RoleEntity.builder()
                                                                      .name(ERole.valueOf(ERole.ADMIN.name()))
                                                                      .build()))
                                              .build();

            UserEntity userEntity2 = UserEntity.builder()
                                               .email("anyi@gmail.com")
                                               .username("anyi")
                                               .password(passwordEncoder.encode("admin"))
                                               .roles(Set.of(RoleEntity.builder()
                                                                       .name(ERole.valueOf(ERole.USER.name()))
                                                                       .build()))
                                               .build();
            userRepository.save(userEntity);
            userRepository.save(userEntity2);
        };
    }

}
