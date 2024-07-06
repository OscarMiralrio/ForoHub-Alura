package com.alura.forohub.domain.topics;

import jakarta.validation.constraints.NotBlank;

public record TopicDTO(
        @NotBlank
        String message,
        @NotBlank
        String title,
        @NotBlank
        String course
) {
}
