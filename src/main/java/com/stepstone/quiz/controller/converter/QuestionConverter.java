package com.stepstone.quiz.controller.converter;

import com.stepstone.quiz.api.response.QuestionDto;
import com.stepstone.quiz.data.Question;
import org.springframework.stereotype.Component;

/**
 * Converts questions to API format
 */
@Component
public class QuestionConverter {
    public QuestionDto convert(Question question) {
        return new QuestionDto(question.getId(), question.getType(), question.getText(), question.getAnswer());
    }
}
