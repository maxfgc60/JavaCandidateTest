package com.stepstone.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Question Controller class
 */
@RestController
public class QuestionController {

    @Autowired
    public QuestionController(){
    }

    @GetMapping("/questions")
    public List<String> getAll(){
        return Collections.emptyList();
    }
}
