package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {

	Set<Questions> findByQuiz(Quiz quiz);

}
