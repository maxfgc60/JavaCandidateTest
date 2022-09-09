package com.stepstone.quiz.service;

import com.stepstone.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Question Service implementation
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(final QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<String> getAll() {
        return Collections.emptyList();
    }
}
