package com.stepstone.quiz.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Answer validation response
 */
public class CheckResponse implements Response {
    private final boolean valid;

    public CheckResponse(boolean valid) {
        this.valid = valid;
    }

    @JsonProperty("valid")
    public boolean isValid() {
        return valid;
    }
}
