package com.stepstone.quiz.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * Answer validation request
 */
public class CheckRequest {

    @NotBlank(message = "answer is mandatory")
    private final String answer;

    @JsonCreator
    public CheckRequest(@JsonProperty("answer") String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
