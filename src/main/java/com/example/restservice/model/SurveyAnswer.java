package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class SurveyAnswer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long submitId;

    @NotNull
    private int score;

    @NotBlank
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "surveyId")
    @JsonBackReference
    private Survey survey;

    protected SurveyAnswer() {}

    public SurveyAnswer(Survey survey, @JsonProperty("score") int score, @JsonProperty("feedback") String feedback) {
        this.score = score;
        this.feedback = feedback;
        this.survey = survey;
    }

    public long getSubmitId() {
        return submitId;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public Survey getSurvey() {return survey;}
}