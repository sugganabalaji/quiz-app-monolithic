package com.app.repository;

import com.app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategoryIgnoreCase(String category);


    @Query(value = "SELECT * FROM Question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :noOfQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noOfQ);
}
