package com.dba.quizapp.controllers.api;

import org.springframework.web.bind.annotation.RestController;

import com.dba.quizapp.model.Questions;
import com.dba.quizapp.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("allQuestions")
    public List<Questions> getAllQiestions() {
        return service.getAllQuestions();
    }

}
