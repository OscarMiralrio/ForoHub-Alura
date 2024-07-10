package com.alura.forohub.domain.topics;

import java.time.LocalDateTime;

public record UpdateTopicDTO (
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha
){
}
