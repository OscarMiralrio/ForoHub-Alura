package com.alura.forohub.controller;

import com.alura.forohub.domain.login.LoginDTO;
import com.alura.forohub.domain.login.User;
import com.alura.forohub.infra.security.JwtTokenDTO;
import com.alura.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Inicio de sesión.")
    @PostMapping
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO loginDTO
    ){
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(),
                loginDTO.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        log.info("Se autentica el usuario ingresado...");
        var jwtToken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        log.info("Se genera token de sesión...");
        return ResponseEntity.ok(new JwtTokenDTO(jwtToken));
    }

}
