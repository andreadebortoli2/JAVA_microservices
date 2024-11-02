package com.dba.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dba.quizapp.model.Questions;
import com.dba.quizapp.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public List<Questions> getAllQuestions() {
        return repo.findAll();
    }

}
