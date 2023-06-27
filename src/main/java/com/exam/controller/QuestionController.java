package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionsService;
import com.exam.service.QuizService;




@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionsService  service;
	
	@Autowired
	private QuizService  quizService;
	
	//add questions
	@PostMapping("/")
	 public ResponseEntity<Questions> add(@RequestBody Questions question){
		 return ResponseEntity.ok(this.service.addQuestions(question));
	 }
	 
	 //update question
	@PutMapping("/")
	 public ResponseEntity<Questions> update (@RequestBody Questions question){
		
		return ResponseEntity.ok(this.service.updateQuestions(question));
	}
	
	//get questions
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz (@PathVariable("qid")Long qid){
       
		
		Quiz quiz = this.quizService.getQuiz(qid);
        Set<Questions> questions= quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list =list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions() +1 ));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
	
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid")Long qid){
		
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		
		Set<Questions>questionOfQuiz= this.service.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
	}


    //get Single Question
	@GetMapping("/{quesId}")
    public Questions get(@PathVariable("quesId") Long quesId){
        return this.service.getQuestions(quesId);
    }



    //delete Question
    @DeleteMapping("/{quesId}")
    public void deleteQuestion (@PathVariable("quesId") Long quesId){
        this.service.deleteQuestion(quesId);
    }



	
	}
	
