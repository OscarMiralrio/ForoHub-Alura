package com.alura.forohub.domain.service;

import com.alura.forohub.commons.constants.ApiConstants;
import com.alura.forohub.domain.topics.DetailTopicDTO;
import com.alura.forohub.domain.topics.Topic;
import com.alura.forohub.domain.topics.TopicDTO;
import com.alura.forohub.domain.topics.TopicRepository;
import com.alura.forohub.infra.exceptions.ValidationException;
import com.alura.forohub.infra.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DetailTopicDTO addTopic(HttpHeaders headers, TopicDTO topicDTO){
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
        return new DetailTopicDTO(topic);
    }



}
