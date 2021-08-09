package com.example.restservice.surveyinterface;

import com.example.restservice.model.SurveyAnswer;
import com.example.restservice.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic,Long> {
    Optional<Topic> findByName(String name);

    @Query("select count(c) "
            + "from Topic f "
            + "inner join f.surveys u "
            + "inner join u.surveyAnswers c "
            + "where f.topicId = ?1 and c.score > 8")
    int count_positive_score (long topicId);

    @Query("select count(c) "
            + "from Topic f "
            + "inner join f.surveys u "
            + "inner join u.surveyAnswers c "
            + "where f.topicId = ?1 and c.score < 7")
    int count_negative_score (long topicId);

    @Query("select count(c) "
            + "from Topic f "
            + "inner join f.surveys u "
            + "inner join u.surveyAnswers c "
            + "where f.topicId = ?1")
    int count_total_answers (long topicId);


    @Query("select c "
            + "from Topic f "
            + "inner join f.surveys u "
            + "inner join u.surveyAnswers c "
            + "where f.topicId = ?1")
    List<SurveyAnswer> getSurveyAnswersByTopic (long topicId);
}
