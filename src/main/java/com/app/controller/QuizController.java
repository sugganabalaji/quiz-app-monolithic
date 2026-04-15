package com.app.controller;

import com.app.entity.Quiz;
import com.app.model.QuestionModel;
import com.app.model.UserResponse;
import com.app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int noOfQ,
                                             @RequestParam String title) {
        Quiz createdQuiz = quizService.createQuiz(category, noOfQ, title);
        return new ResponseEntity<>("Quiz Created with ID: " + createdQuiz.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionModel>> getQuestionsByQuizId(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<QuestionModel> questions = quizService.getQuizQuestions(id);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable Long quizId,
                                             @RequestBody List<UserResponse> userResponses) {
        int score = quizService.calculateResult(quizId, userResponses);
        return new ResponseEntity<>("Your score: " + score, HttpStatus.OK);
    }
}
