package com.alura.forohub.controller;

import com.alura.forohub.domain.topics.TopicDTO;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@Slf4j
public class TopicController {

    @PostMapping
    public ResponseEntity addTopic(
            @RequestHeader @Parameter(hidden = true) HttpHeaders headers,
            @RequestBody @Valid TopicDTO topic
            ){
        log.info("Inicio");

        log.info(("Fin"));
        return ResponseEntity.ok().build();
    }

}
