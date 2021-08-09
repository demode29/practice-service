package com.example.restservice.surveyinterface;

import com.example.restservice.model.Survey;
import com.example.restservice.model.SurveyAnswer;
import com.example.restservice.model.Topic;

import java.util.List;

public interface SurveyInterface {
    int addSurvey(Survey survey);
    int submitSurveyAnswer(long topicId, SurveyAnswer surveyAnswer);
    Iterable<Survey> getSurveys();
    Iterable<Topic> getTopics();

    Iterable<Survey> getSurveysByTopic(long topicId);
    Iterable<SurveyAnswer> getSurveyAnswersByTopic(long topicId);
}
