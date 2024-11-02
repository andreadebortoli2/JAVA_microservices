package com.dba.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dba.quizapp.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
