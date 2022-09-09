package com.stepstone.quiz.service;

import com.stepstone.quiz.repository.QuestionRepository;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * Tests for Question Service
 */
public class QuestionServiceTest {

    @Test
    public void getAll_should_return_questions(){
        // given
        final var mockQuestionRepository = mock(QuestionRepository.class);
        final var questionService = new QuestionServiceImpl(mockQuestionRepository);

        // when
        final var questions = questionService.getAll();

        // then
        assertThat(questions, is(notNullValue()));
    }
}
