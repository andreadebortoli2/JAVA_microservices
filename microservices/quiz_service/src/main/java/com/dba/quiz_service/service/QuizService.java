package com.dba.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dba.quiz_service.model.Answer;
import com.dba.quiz_service.model.QuestionWrapper;
import com.dba.quiz_service.model.Quiz;
import com.dba.quiz_service.repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    QuizRepo repo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = repo.findById(id);

        List<Question> questionsFromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Answer> answers) {

        Optional<Quiz> quiz = repo.findById(id);

        List<Question> questions = quiz.get().getQuestions();

        int correctAnswers = 0;
        int i = 0;

        for (Answer a : answers) {
            if (a.getAnswer().equals(questions.get(i).getCorrectAnswer())) {
                correctAnswers++;
            }
            i++;
        }

        return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
    }

}
