package com.stepstone.quiz.controller.converter;

import com.stepstone.quiz.service.result.ErrorType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ErrorConverterTest {

    @Test
    public void shouldConvertAllErrorTypesSuccessfully() {
        var converter = new ErrorConverter();
        for (var error : ErrorType.values()) {
            converter.convert(error);
        }
    }

    @Test
    public void shouldCorrectlyConvertErrorsToStatusCodes() {
        var converter = new ErrorConverter();
        assertThat(converter.convert(ErrorType.QUESTION_NOT_FOUND), is(HttpStatus.NOT_FOUND));
    }
}