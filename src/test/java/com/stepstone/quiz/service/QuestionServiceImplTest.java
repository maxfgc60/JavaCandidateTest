package com.stepstone.quiz.service;

import com.stepstone.quiz.data.Question;
import com.stepstone.quiz.repository.QuestionRepository;
import com.stepstone.quiz.service.result.ErrorType;
import com.stepstone.quiz.service.result.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {
    private static final Question QUESTION_1 = new Question("1", "topic1", "Q1?", "Ans1");
    private static final Question QUESTION_2 = new Question("2", "topic1", "Q2?", "Ans2");
    private static final Question QUESTION_3 = new Question("3", "topic2", "Q3?", "Ans3");

    private final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private final QuestionService questionService = new QuestionServiceImpl(questionRepository);

    @BeforeEach
    public void setup() {
        Mockito.reset(questionRepository);
    }

    @Test
    public void getAllShouldReturnAllQuestions() {
        when(questionRepository.findAll()).thenReturn(List.of(QUESTION_1, QUESTION_2, QUESTION_3));
        var result = questionService.getAll();
        assertThat(result, Matchers.instanceOf(Result.Ok.class));
        var questions = ((Result.Ok<List<Question>>) result).getData();
        assertThat(questions, hasSize(3));
        assertThat(questions, containsInAnyOrder(QUESTION_1, QUESTION_2, QUESTION_3));
    }

    @Test
    public void checkShouldValidateExistingQuestion() {
        when(questionRepository.findById(eq("1"))).thenReturn(Optional.of(QUESTION_1));

        var result1 = questionService.check("1", "Ans1");
        assertThat(result1, Matchers.instanceOf(Result.Ok.class));
        assertThat(((Result.Ok<Boolean>) result1).getData(), is(true));

        var result2 = questionService.check("1", "Incorrect answer");
        assertThat(result2, Matchers.instanceOf(Result.Ok.class));
        assertThat(((Result.Ok<Boolean>) result2).getData(), is(false));
    }

    @Test
    public void checkShouldNotValidateMissingQuestion() {
        when(questionRepository.findById(eq("1"))).thenReturn(Optional.empty());

        var result = questionService.check("1", "Ans1");
        assertThat(result, Matchers.instanceOf(Result.Error.class));
        var error = (Result.Error<Boolean>) result;
        assertThat(error.getType(), is(ErrorType.QUESTION_NOT_FOUND));
        assertThat(error.getMessage(), is("question 1 not found"));
    }

    @Test
    public void getByTypeShouldReturnCorrectQuestions() {
        when(questionRepository.findByType(eq("topic1"))).thenReturn(List.of(QUESTION_1, QUESTION_2));
        var result = questionService.getByType("topic1");
        assertThat(result, Matchers.instanceOf(Result.Ok.class));
        var questions = ((Result.Ok<List<Question>>) result).getData();
        assertThat(questions, hasSize(2));
        assertThat(questions, containsInAnyOrder(QUESTION_1, QUESTION_2));
    }
}