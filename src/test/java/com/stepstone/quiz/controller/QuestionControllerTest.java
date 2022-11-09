package com.stepstone.quiz.controller;

import com.stepstone.quiz.controller.converter.ErrorConverter;
import com.stepstone.quiz.controller.converter.QuestionConverter;
import com.stepstone.quiz.data.Question;
import com.stepstone.quiz.service.QuestionService;
import com.stepstone.quiz.service.result.ErrorType;
import com.stepstone.quiz.service.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTest {
    private static final Question QUESTION = new Question("1", "topic", "Q?", "Ans");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @SpyBean
    private QuestionConverter questionConverter;

    @SpyBean
    private ErrorConverter errorConverter;

    @BeforeEach
    public void init() {
        Mockito.reset(questionService);
    }

    @Test
    public void shouldReturnAllQuestions() throws Exception {
        Mockito.when(questionService.getAll()).thenReturn(new Result.Ok<>(List.of(QUESTION)));
        mockMvc.perform(get("/api/v1/questions"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.questions[0].id").value("1"))
                .andExpect(jsonPath("$.questions[0].type").value("topic"))
                .andExpect(jsonPath("$.questions[0].text").value("Q?"))
                .andExpect(jsonPath("$.questions[0].answer").value("Ans"));
    }

    @Test
    public void shouldFilterQuestionsByType() throws Exception {
        Mockito.when(questionService.getByType(eq("topic"))).thenReturn(new Result.Ok<>(List.of(QUESTION)));
        Mockito.when(questionService.getByType(eq("other_topic"))).thenReturn(new Result.Ok<>(List.of()));

        mockMvc.perform(get("/api/v1/questions?type=topic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questions[0].id").value("1"));

        mockMvc.perform(get("/api/v1/questions?type=other_topic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questions.length()").value(0));
    }

    @Test
    public void shouldValidateExistingQuestions() throws Exception {
        Mockito.when(questionService.check(eq("1"), eq("Ans"))).thenReturn(new Result.Ok<>(true));
        mockMvc.perform(post("/api/v1/questions/1/check")
                        .contentType("application/json")
                        .content("{\"answer\": \"Ans\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.valid").value("true"));
    }

    @Test
    public void shouldNotValidateMissingQuestions() throws Exception {
        Mockito.when(questionService.check(eq("2"), eq("14"))).thenReturn(
                new Result.Error<>(ErrorType.QUESTION_NOT_FOUND, "err msg"));

        mockMvc.perform(post("/api/v1/questions/2/check")
                        .contentType("application/json")
                        .content("{\"answer\": \"14\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("err msg"));
    }

    @Test
    public void shouldFailOnInvalidCheckRequest() throws Exception {
        mockMvc.perform(post("/api/v1/questions/2/check")
                        .contentType("application/json")
                        .content("{\"answwwww\": \"14\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("answer is mandatory"));
    }
}