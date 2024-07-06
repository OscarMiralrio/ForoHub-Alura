package com.alura.forohub.infra.security;

import com.alura.forohub.domain.login.User;
import com.alura.forohub.infra.exceptions.TokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(User user){
        String sing = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            sing = JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new TokenException(exception.getMessage());
        }
        return sing;
    }

    public String getSubject(String token){

        if (token == null){
            throw new RuntimeException("");
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            log.error(exception.toString());
        }
        if (verifier == null) {
            throw new TokenException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    public String gerUsername(String token){
        String subject = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            subject = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenException("Verifier invalido");
        }
        return subject;
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
