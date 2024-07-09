package com.alura.forohub.domain.topics;

public record DetailsAllTopicsDTO(
        String title,
        String message,
        String date,
        String status,
        String author,
        String course
) {
    public DetailsAllTopicsDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), String.valueOf(topic.getDate()),topic.getStatus().name(), topic.getAuthor(), topic.getCourse());
    }
}
