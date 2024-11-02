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

    public List<Questions> getQuestionsByCategory(String category) {
        return repo.findByCategory(category);
    }

    public String addOrUpdateQuestion(Questions question) {
        repo.save(question);
        return "success";
    }

    public String deleteQuestion(int id) {
        repo.deleteById(id);
        return "success";
    }

}
