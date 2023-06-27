package com.exam.service;

import java.util.Set;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;

public interface QuestionsService {
	
	public Questions addQuestions(Questions questions);
	
	public Questions updateQuestions(Questions questions);
	
	public Set<Questions>getQuestions();
	
	public Questions getQuestions(Long questionId);
	
	public Set<Questions>getQuestionsOfQuiz(Quiz quiz);

	public void deleteQuestion(Long quesId);

}
