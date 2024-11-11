package com.keepcoding.proyecto.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keepcoding.proyecto.dto.LoginUserDto;
import com.keepcoding.proyecto.dto.RegisterUserDto;
import com.keepcoding.proyecto.entity.User;
import com.keepcoding.proyecto.repository.UserRepository;



@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    
    public User signup(RegisterUserDto input) {
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto loginDto) {
        try {
        	
            System.out.println("Autenticando usuario: " + loginDto.getEmail());
            
            
            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("No encontrado"));
            
           
            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                throw new RuntimeException("contraseña incorrecta");
                
            }
            
           
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                            
                    )
            );
            
            
            if (authentication.isAuthenticated()) {
                System.out.println("Autenticacion exitosa");
                return user;
            } else {
                throw new RuntimeException("Error en la autenticación");
            }
            
        } catch (Exception e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            throw new RuntimeException("Fallo la autenticación: " + e.getMessage());
        }
    }
}