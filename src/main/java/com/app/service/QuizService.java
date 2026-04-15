package com.app.service;

import com.app.entity.Question;
import com.app.entity.Quiz;
import com.app.model.QuestionModel;
import com.app.model.UserResponse;
import com.app.repository.QuestionRepository;
import com.app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(String category, int noOfQ, String title) {
        List<Question> question = questionRepository.findRandomQuestionsByCategory(category, noOfQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public List<QuestionModel> getQuizQuestions(Long id) {
        Optional<Quiz> quizDb = quizRepository.findById(id);
        if(quizDb.isPresent()) {
            Quiz quiz = quizDb.get();
            return quiz.getQuestions().stream()
                    .map(question -> new QuestionModel(question.getId(),
                                                                question.getQuestionTitle(),
                                                                question.getOption1(),
                                                                question.getOption2(),
                                                                question.getOption3(),
                                                                question.getOption4())
                    ).toList();
        }
        return null;
    }

    public int calculateResult(Long quizId, List<UserResponse> userResponses) {
        Optional<Quiz> byId = quizRepository.findById(quizId);
        int score = 0;
        if(byId.isPresent()) {
            Quiz quiz = byId.get();
            List<Question> questionsDB = quiz.getQuestions();
            for (UserResponse userResponse : userResponses) {
                for (Question question : questionsDB) {
                    if (Objects.equals(question.getId(), userResponse.getQuestionId()) &&
                            question.getRightAnswer().equalsIgnoreCase(userResponse.getUserSelected())) {

                        score++;
                    }
                }
            }
        }
        return score;
    }
}
