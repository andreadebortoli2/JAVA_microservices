package com.dba.question_service.controllers.api;

import org.springframework.web.bind.annotation.RestController;

import com.dba.question_service.model.Answer;
import com.dba.question_service.model.Question;
import com.dba.question_service.model.QuestionWrapper;
import com.dba.question_service.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQiestions() {
        return service.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return service.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return service.addOrUpdateQuestion(question);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        return service.addOrUpdateQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return service.deleteQuestion(id);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
            @RequestParam Integer numQuestions) {
        return service.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return service.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answer> answers) {
        return service.getScore(answers);
    }

}
