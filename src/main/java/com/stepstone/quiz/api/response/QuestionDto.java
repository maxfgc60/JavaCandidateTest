package com.stepstone.quiz.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Question DTO
 */
public class QuestionDto {
    private final String id;
    private final String type;
    private final String text;
    private final String answer;

    public QuestionDto(String id,
                       String type,
                       String text,
                       String answer) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.answer = answer;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("answer")
    public String getAnswer() {
        return answer;
    }
}
