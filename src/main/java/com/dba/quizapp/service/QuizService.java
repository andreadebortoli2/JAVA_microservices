package com.dba.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dba.quizapp.model.Questions;
import com.dba.quizapp.model.Quiz;
import com.dba.quizapp.repository.QuestionRepo;
import com.dba.quizapp.repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    QuizRepo repo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Questions> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            repo.save(quiz);

            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

}
