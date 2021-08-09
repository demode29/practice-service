package com.example.restservice.surveyinterface;
import com.example.restservice.model.Survey;
import com.example.restservice.model.SurveyAnswer;
import com.example.restservice.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends CrudRepository<Survey,Long> {
    List<Survey> findByTopic(Topic topic);

    Optional<Topic> findBySurveyId(long surveyId);
}