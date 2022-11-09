package com.stepstone.quiz.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response
 */
public class ErrorResponse implements Response {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }
}
