package com.stepstone.quiz.controller;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Tests for Question Controller
 */
public class QuestionControllerTest {

    @Test
    public void getQuestions_should_return_questions() {
        // given
        final var questionController = new QuestionController();

        //when
        final var questions = questionController.getAll();

        // then
        assertThat(questions, is(notNullValue()));
    }

}
