package com.alura.forohub.domain.topics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByTitleEqualsAndMessageEquals(String title, String message);

    Page<Topic> findAll(Pageable pageable);

}
