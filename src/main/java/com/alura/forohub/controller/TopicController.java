package com.alura.forohub.controller;

import com.alura.forohub.commons.constants.ApiConstants;
import com.alura.forohub.domain.service.TopicService;
import com.alura.forohub.domain.topics.DetailTopicDTO;
import com.alura.forohub.domain.topics.TopicDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@Slf4j
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Operation(summary = "Crea nuevo tópico")
    @PostMapping
    public ResponseEntity<?> addTopic(
            @RequestHeader @Parameter(hidden = true) HttpHeaders headers,
            @RequestBody @Valid TopicDTO topicDTO){
        log.info(ApiConstants.INICIO_LOG);
        var topic = topicService.addTopic(headers,topicDTO);
        log.info(ApiConstants.FIN_LOG);
        return ResponseEntity.ok().body(topic);
    }

    @Operation(summary = "Obtiene todos los tópicos con paginación")
    @GetMapping
    public ResponseEntity<Page<DetailTopicDTO>> getAllTopics(
            @PageableDefault(size = 10, sort = {"date"}) Pageable pageable
            ){
        log.info(ApiConstants.INICIO_LOG);
        var topics = topicService.getAllTopics(pageable);
        log.info(ApiConstants.FIN_LOG);
        return ResponseEntity.ok().body(topics);
    }

    @Operation(summary = "Obtiene detalle de tópico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DetailTopicDTO> getDetailTopic(
            @Parameter( description = "ID del tópico")
            @PathVariable Long id){
        log.info(ApiConstants.INICIO_LOG);
        var topic = topicService.getDetailTopic(id);
        log.info(ApiConstants.FIN_LOG);
        return ResponseEntity.ok(topic);
    }

    @Operation(summary = "Actuliza tópico")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailTopicDTO> updateTopic(
            @Parameter(description = "ID del tópico")
            @PathVariable Long id,
            @RequestBody @Valid TopicDTO updateTopicDTO){
        log.info(ApiConstants.INICIO_LOG);
        var updateTopic = topicService.updateTopic(id,updateTopicDTO);
        log.info(ApiConstants.FIN_LOG);
        return ResponseEntity.ok(updateTopic);
    }

    @Operation(summary = "Elimina tópico por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(
            @Parameter(description = "ID del tópico")
            @PathVariable Long id){
        log.info(ApiConstants.INICIO_LOG);
        topicService.deleteTopic(id);
        log.info(ApiConstants.FIN_LOG);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
