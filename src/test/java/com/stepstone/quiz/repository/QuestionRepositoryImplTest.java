package com.stepstone.quiz.repository;

import com.stepstone.quiz.data.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuestionRepositoryImplTest {
    private static final Question QUESTION_1 = new Question("1", "topic1", "Q1?", "Ans1");
    private static final Question QUESTION_2 = new Question("2", "topic1", "Q2?", "Ans2");
    private static final Question QUESTION_3 = new Question("3", "topic2", "Q3?", "Ans3");

    private QuestionRepository questionRepository;

    @BeforeEach
    public void setup() {
        questionRepository = new QuestionRepositoryImpl(List.of(QUESTION_1, QUESTION_2, QUESTION_3));
    }

    @Test
    public void findAllShouldReturnAllQuestions() {
        var questions = questionRepository.findAll();
        assertThat(questions, hasSize(3));
        assertThat(questions, containsInAnyOrder(QUESTION_1, QUESTION_2, QUESTION_3));
    }

    @Test
    public void findByIdShouldReturnExistingQuestion() {
        var question = questionRepository.findById("1");
        assertThat(question.isPresent(), is(true));
        assertThat(question.get(), is(QUESTION_1));
    }

    @Test
    public void findByIdShouldNotReturnMissingQuestion() {
        var question = questionRepository.findById("0");
        assertThat(question.isPresent(), is(false));
    }

    @Test
    public void findByTypeShouldReturnCorrectQuestions() {
        var questions = questionRepository.findByType("topic1");
        assertThat(questions, hasSize(2));
        assertThat(questions, containsInAnyOrder(QUESTION_1, QUESTION_2));
    }
}