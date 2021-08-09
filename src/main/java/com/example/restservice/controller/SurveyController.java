package com.example.restservice.controller;

import com.example.restservice.model.Survey;
import com.example.restservice.model.SurveyAnswer;
import com.example.restservice.surveyinterface.SurveyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SurveyController {
    @Autowired
    SurveyInterface databaseService;

    @GetMapping(value = "/surveys")
    public ResponseEntity<Object> getSurveys() {
        return new ResponseEntity<>(databaseService.getSurveys(), HttpStatus.OK);
    }

    @GetMapping(value = "/topics")
    public ResponseEntity<Object> getTopics() {
        return new ResponseEntity<>(databaseService.getTopics(), HttpStatus.OK);
    }

    @PostMapping(value = "/createSurveys")
    public ResponseEntity<Object> addSurvey (@Valid @RequestBody Survey survey) {
        databaseService.addSurvey(survey);

        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }

    @PostMapping(value = "/submitAnswer")
    public ResponseEntity<Object> submitAnswer(@RequestParam long surveyId, @Valid @RequestBody SurveyAnswer surveyAnswer) {
        databaseService.submitSurveyAnswer(surveyId, surveyAnswer);

        return new ResponseEntity<>("Submitted Success", HttpStatus.OK);
    }

    @GetMapping(value = "/getSurveysByTopic")
    public ResponseEntity<Object> getSurveysByTopic(@RequestParam long topicId) {
        return new ResponseEntity<>(databaseService.getSurveysByTopic(topicId), HttpStatus.OK);
    }

    @GetMapping(value = "/getSurveyAnswersByTopic")
    public ResponseEntity<Object> getSurveyAnswersByTopic(@RequestParam long topicId) {
        return new ResponseEntity<>(databaseService.getSurveyAnswersByTopic(topicId), HttpStatus.OK);
    }
}
