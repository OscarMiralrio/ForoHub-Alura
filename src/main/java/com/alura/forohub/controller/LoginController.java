package com.alura.forohub.controller;

import com.alura.forohub.domain.login.LoginDTO;
import com.alura.forohub.domain.login.User;
import com.alura.forohub.infra.security.JwtTokenDTO;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
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
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

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

        var jwtToken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDTO(jwtToken));
    }

}
