package com.alura.forohub.domain.topics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String author;
    private String course;


    public Topic(TopicDTO topicDTO, String subject) {
        this.title = topicDTO.title();
        this.message = topicDTO.message();
        this.date = LocalDateTime.now();
        this.status = Status.NUEVO;
        this.author = subject;
        this.course = topicDTO.course();
    }

    public void updateTopic(TopicDTO topicDTO){
        this.title = topicDTO.title();
        this.message = topicDTO.message();
        this.course = topicDTO.course();
        this.date = LocalDateTime.now();
        status = Status.MODIFICADO;
    }

}
