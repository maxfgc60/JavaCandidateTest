package com.stepstone.quiz.service;

import com.stepstone.quiz.data.Question;
import com.stepstone.quiz.service.result.Result;

import java.util.List;

/**
 *  Question Service Interface
 */
public interface QuestionService {
    Result<List<Question>> getAll();

    Result<Boolean> check(String id, String answer);

    Result<List<Question>> getByType(String type);
}
