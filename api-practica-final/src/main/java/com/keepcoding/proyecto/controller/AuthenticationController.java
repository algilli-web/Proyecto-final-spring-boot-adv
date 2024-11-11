package com.keepcoding.proyecto.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keepcoding.proyecto.dto.LoginResponse;
import com.keepcoding.proyecto.dto.LoginUserDto;
import com.keepcoding.proyecto.dto.RegisterUserDto;
import com.keepcoding.proyecto.entity.User;
import com.keepcoding.proyecto.service.AuthenticationService;
import com.keepcoding.proyecto.service.JwtService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    private final AuthenticationService service;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping("/registro")
    public ResponseEntity<LoginResponse> registro(@RequestBody RegisterUserDto registerUserDto) {
        User usuarioRegistrado = service.signup(registerUserDto);
        String token = jwtService.generateToken(usuarioRegistrado);
        
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpiresIn(jwtService.getExpirationTime());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        System.out.println("Intento de login para email: " + loginUserDto.getEmail());
        
        User usuario = service.authenticate(loginUserDto);
        String token = jwtService.generateToken(usuario);
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        return ResponseEntity.ok(loginResponse);
    }
}