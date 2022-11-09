package com.stepstone.quiz.service;

import com.stepstone.quiz.data.Question;
import com.stepstone.quiz.repository.QuestionRepository;
import com.stepstone.quiz.service.result.ErrorType;
import com.stepstone.quiz.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Question Service implementation
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Result<List<Question>> getAll() {
        return new Result.Ok<>(questionRepository.findAll());
    }

    @Override
    public Result<Boolean> check(String id, String answer) {
        var question = questionRepository.findById(id);
        if (question.isEmpty()) {
            return new Result.Error<>(ErrorType.QUESTION_NOT_FOUND, String.format("question %s not found", id));
        }
        return new Result.Ok<>(question.get().getAnswer().equals(answer));
    }

    @Override
    public Result<List<Question>> getByType(String type) {
        return new Result.Ok<>(questionRepository.findByType(type));
    }
}
