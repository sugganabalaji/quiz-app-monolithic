package com.app.service;

import com.app.entity.Question;
import com.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategoryIgnoreCase(category);
    }

    public Question addQuestion(Question question) {
        if(question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            question.setId(null);
            return questionRepository.save(question);
        }
    }

    public boolean deleteQuestion(Long id) {
        boolean isExists = questionRepository.existsById(id);
        if(isExists) {
           questionRepository.deleteById(id);
        }
        return isExists;
    }
}
