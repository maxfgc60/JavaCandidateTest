package com.stepstone.quiz.controller.converter;

import com.stepstone.quiz.service.result.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Converts application errors to HTTP status codes
 */
@Component
public class ErrorConverter {

    public HttpStatus convert(ErrorType type) {
        switch (type) {
            case QUESTION_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            default:
                throw new IllegalArgumentException("unknown error type: " + type);
        }
    }
}
