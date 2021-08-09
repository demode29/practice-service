package com.example.restservice.surveyinterface;

import com.example.restservice.model.Survey;
import com.example.restservice.model.SurveyAnswer;
import com.example.restservice.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseAccessService implements SurveyInterface {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public int addSurvey(Survey survey) {
        Optional<Topic> foundTopic = topicRepository.findByName(survey.getTopic().getName());
        Topic newTopic;

        if(!foundTopic.isPresent()) {
            newTopic = new Topic(survey.getTopic().getName(), 0);
        } else {
            newTopic = foundTopic.get();
        }

        List<Survey> newTopicSurveys = newTopic.getSurveys();

        newTopicSurveys.add(new Survey(newTopic,survey.getQuestion()));

        newTopic.setSurveys(newTopicSurveys);

        topicRepository.save(newTopic);

        return 0;
    }

    @Override
    public int submitSurveyAnswer(long surveyId, SurveyAnswer surveyAnswer) {
        Optional<Survey> foundSurvey = surveyRepository.findById(surveyId);

        if(foundSurvey.isPresent()) {
            Survey newSurvey = foundSurvey.get();

            List<SurveyAnswer> currentSurveyAnswers = newSurvey.getSurveyAnswers();

            currentSurveyAnswers.add(new SurveyAnswer(newSurvey, surveyAnswer.getScore(), surveyAnswer.getFeedback()));

            newSurvey.setSurveyAnswers(currentSurveyAnswers);

            surveyRepository.save(newSurvey);

            //recalculate topic nps score by survey
            Topic foundTopic = newSurvey.getTopic();

            recalculateNpmScore(foundTopic);
        }
        return 0;
    }

    public void recalculateNpmScore(Topic foundTopic){
        long topicId = foundTopic.getTopicId();

        int totalQuestions = topicRepository.count_total_answers(topicId);
        int totalPositives = topicRepository.count_positive_score(topicId);
        int totalNegatives = topicRepository.count_negative_score(topicId);

        int posPercentage = (int)(((double)totalPositives / totalQuestions) * 100);
        int negPercentage = (int)(((double)totalNegatives / totalQuestions) * 100);

        foundTopic.setNpmScore(posPercentage - negPercentage);
        topicRepository.save(foundTopic);
    }

    @Override
    public Iterable<Survey> getSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Iterable<Topic> getTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Iterable<Survey> getSurveysByTopic(long topicId) {
        Optional<Topic> foundTopic = topicRepository.findById(topicId);

        if(foundTopic.isPresent()) {
            List<Survey> surveys = surveyRepository.findByTopic(foundTopic.get());

            return surveys;
        }

        return null;
    }

    @Override
    public Iterable<SurveyAnswer> getSurveyAnswersByTopic(long topicId) {
        return topicRepository.getSurveyAnswersByTopic(topicId);
    }
}