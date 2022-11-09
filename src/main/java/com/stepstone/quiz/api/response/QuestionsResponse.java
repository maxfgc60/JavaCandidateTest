package com.stepstone.quiz.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Questions response
 */
public class QuestionsResponse implements Response {
    private final List<QuestionDto> questions;

    public QuestionsResponse(List<QuestionDto> questions) {
        this.questions = questions;
    }

    @JsonProperty("questions")
    public List<QuestionDto> getQuestions() {
        return questions;
    }
}
