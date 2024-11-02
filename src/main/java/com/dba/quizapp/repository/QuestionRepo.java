package com.dba.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dba.quizapp.model.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);

}
