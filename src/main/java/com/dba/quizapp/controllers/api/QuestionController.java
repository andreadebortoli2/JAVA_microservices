package com.dba.quizapp.controllers.api;

import org.springframework.web.bind.annotation.RestController;

import com.dba.quizapp.model.Questions;
import com.dba.quizapp.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("allQuestions")
    public List<Questions> getAllQiestions() {
        return service.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable String category) {
        return service.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Questions question) {
        if (service.addOrUpdateQuestion(question) == "success") {
            return "Question added";
        } else {
            return "something went wrong, qestion not added";
        }
    }

    @PutMapping("update")
    public String updateQuestion(@RequestBody Questions question) {
        if (service.addOrUpdateQuestion(question) == "success") {
            return "Question updated";
        } else {
            return "something went wrong, qestion not updated";
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
        if (service.deleteQuestion(id) == "success") {
            return "Question deleted";
        } else {
            return "something went wrong, qestion not deleted";
        }
    }

}
