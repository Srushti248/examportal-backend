package com.exam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionsRepository;


@Service
public class QuestionsServiceImpl  implements QuestionsService{

	@Autowired
	private QuestionsRepository questionRepository;
	
	@Override
	public Questions addQuestions(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public Questions updateQuestions(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public Set<Questions> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.questionRepository.findAll());
	}

	
	public Questions getQuestions(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepository.findById(questionId).get();
		}

	@Override
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		// TODO Auto-generated method stub
		
         Questions question= new Questions();
		
		question.setQuesId(quesId);
		
		this.questionRepository.delete(question);
		
	}

	
	}

