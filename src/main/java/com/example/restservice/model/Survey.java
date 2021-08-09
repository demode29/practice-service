package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="surveyId")
    private Long surveyId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "topicId")
    @JsonBackReference
    private Topic topic;

    @NotBlank
    @Column(name = "question")
    private String question;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy="survey")
    @JsonManagedReference
    private List<SurveyAnswer> surveyAnswers;

    protected Survey() {}

    public Survey( @JsonProperty("topic") Topic topic, @JsonProperty("question") String question) {
        this.topic = topic;
        this.question = question;

        this.surveyAnswers = new ArrayList<>();
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public String getQuestion() {
        return question;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<SurveyAnswer> getSurveyAnswers () {
        return surveyAnswers;
    }

    public void setSurveyAnswers (List<SurveyAnswer> surveyAnswers) {
        this.surveyAnswers = surveyAnswers;
    }
}
