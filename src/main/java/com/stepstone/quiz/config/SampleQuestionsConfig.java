package com.stepstone.quiz.config;

import com.stepstone.quiz.data.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Sample questions and answers
 */
@Configuration
public class SampleQuestionsConfig {

    @Bean
    public List<Question> qetSampleQuestions() {
        return List.of(
                new Question("1", "math", "2 + 2 = ...", "4"),
                new Question("2", "math", "3 * 9 = ...", "27"),

                new Question("3", "geography", "What is the capital of France?", "Paris"),
                new Question("4", "geography", "What is the capital of Germany?", "Berlin"),

                new Question("5", "physics", "Who proposed the general theory of relativity?", "Albert Einstein"),
                new Question("6", "physics", "What is used to split white light into different colors?", "prism")
        );
    }
}
