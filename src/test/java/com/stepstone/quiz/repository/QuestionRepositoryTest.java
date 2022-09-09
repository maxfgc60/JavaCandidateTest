package com.stepstone.quiz.repository;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Tests for Question Repository
 */

public class QuestionRepositoryTest {

    @Test
    public void findAll_should_return_questions(){
        // given
        final var questionRepository = new QuestionRepositoryImpl();

        // when
        final var questions = questionRepository.findAll();

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(4)));
    }
}
