package com.alura.forohub.domain.topics;

import java.time.LocalDateTime;

public record NewTopicDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha
) {
    public NewTopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getDate());
    }
}
