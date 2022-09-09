package com.stepstone.quiz.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Static Question Repository implementation
 */
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    public List<String> findAll() {
        // Geography quiz questions...
        return List.of(
                "What is the capital of Cuba?",
                "What is the capital of France?",
                "What is the capital of Poland?",
                "What is the capital of Germany?");
    }
}
