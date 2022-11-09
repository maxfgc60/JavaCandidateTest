package com.stepstone.quiz.repository;

import com.stepstone.quiz.data.Question;

import java.util.List;
import java.util.Optional;

/**
 * Question Repository Interface
 */
public interface QuestionRepository {
    List<Question> findAll();

    Optional<Question> findById(String id);

    List<Question> findByType(String type);
}
