package com.stepstone.quiz.repository;

import com.stepstone.quiz.data.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Static Question Repository implementation
 */
@Repository
@Component
public class QuestionRepositoryImpl implements QuestionRepository {
    private final Map<String, Question> idToQuestion;

    public QuestionRepositoryImpl(@Autowired Collection<Question> questions) {
        this.idToQuestion = questions.stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(idToQuestion.values());
    }

    @Override
    public Optional<Question> findById(String id) {
        return Optional.ofNullable(idToQuestion.get(id));
    }

    @Override
    public List<Question> findByType(String type) {
        return idToQuestion.values().stream()
                .filter(q -> type.equals(q.getType()))
                .collect(Collectors.toList());
    }
}
