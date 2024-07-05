package com.alura.forohub.domain.topics;

import java.time.LocalDateTime;

public record DetailTopicDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha
) {
    public DetailTopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getDate());
    }
}
