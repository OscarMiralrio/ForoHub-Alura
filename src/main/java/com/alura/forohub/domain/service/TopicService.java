package com.alura.forohub.domain.service;

import com.alura.forohub.commons.constants.ApiConstants;
import com.alura.forohub.domain.topics.*;
import com.alura.forohub.infra.exceptions.ValidationException;
import com.alura.forohub.infra.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TokenService tokenService;

    public NewTopicDTO addTopic(HttpHeaders headers, TopicDTO topicDTO){
        log.info(ApiConstants.INICIO_LOG);
        var duplicateTopics = topicRepository.findByTitleEqualsAndMessageEquals(topicDTO.title(),topicDTO.message()).size();
        if (duplicateTopics > 0){
            throw new ValidationException("Tópico Duplicado (con el mismo título y mensaje)");
        }
        var headerAuth = Objects.requireNonNull(headers.getFirst(ApiConstants.AUTHORIZATION))
                                .replace(ApiConstants.BEARER_TOKEN,ApiConstants.EMPTY_STRING);
        var topic = new Topic(topicDTO, tokenService.gerUsername(headerAuth));
        topicRepository.save(topic);
        log.info(ApiConstants.FIN_LOG);
        return new NewTopicDTO(topic);
    }

    public Page<DetailTopicDTO> getAllTopics(Pageable pageable) {
        log.info("Obtiene la lista por paginación de todos los topicos registrados");
        return topicRepository.findAll(pageable).map(DetailTopicDTO::new);
    }

    public DetailTopicDTO getDetailTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        log.info("Se obtiene el detalle del tópico con el id :: " + id);
        return new DetailTopicDTO(topic);
    }
}
