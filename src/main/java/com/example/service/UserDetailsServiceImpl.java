package com.example.service;

import com.example.entities.UserEntity;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            String errorMessage = String.format("User with username %s not found",
                                                username);
            throw new UsernameNotFoundException(errorMessage);
        }
        UserEntity userEntity = optionalUserEntity.get();
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                                                                       .stream()
                                                                       .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName()
                                                                                                                                  .name())))
                                                                       .collect(Collectors.toSet());

        return new User(userEntity.getUsername(),
                        userEntity.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        authorities);
    }
}
