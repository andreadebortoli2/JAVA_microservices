package com.dba.quiz_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dba.quiz_service.feign.QuizInteface;
import com.dba.quiz_service.model.Answer;
import com.dba.quiz_service.model.QuestionWrapper;
import com.dba.quiz_service.model.Quiz;
import com.dba.quiz_service.repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    QuizRepo repo;

    @Autowired
    QuizInteface quizInteface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInteface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        repo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = repo.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questions = quizInteface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Answer> answers) {

        ResponseEntity<Integer> score = quizInteface.getScore(answers);

        return score;
    }

}
