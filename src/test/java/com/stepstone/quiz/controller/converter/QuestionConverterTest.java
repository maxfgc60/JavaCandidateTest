package com.stepstone.quiz.controller.converter;

import com.stepstone.quiz.data.Question;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QuestionConverterTest {

    @Test
    public void shouldCorrectlyConvertQuestionsToDtos() {
        var converter = new QuestionConverter();
        var question = new Question("1", "topic", "Q?", "Ans");
        var questionDto = converter.convert(question);
        assertThat(questionDto.getId(), is("1"));
        assertThat(questionDto.getType(), is("topic"));
        assertThat(questionDto.getText(), is("Q?"));
        assertThat(questionDto.getAnswer(), is("Ans"));
    }
}