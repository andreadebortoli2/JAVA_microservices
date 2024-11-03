package com.dba.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dba.question_service.model.Answer;
import com.dba.question_service.model.Question;
import com.dba.question_service.model.QuestionWrapper;
import com.dba.question_service.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addOrUpdateQuestion(Question question) {
        try {
            repo.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = repo.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionIds) {
            questions.add(repo.findById(id).get());
        }

        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            wrappers.add(qw);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Answer> answers) {

        Integer score = 0;

        for (Answer a : answers) {
            Optional<Question> q = repo.findById(a.getId());
            if (q.get().getCorrectAnswer().equals(a.getAnswer())) {
                score++;
            }
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }

}
