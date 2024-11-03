package com.dba.quiz_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dba.quiz_service.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
