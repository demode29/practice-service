package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Topic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="topicId")
    private long topicId;

    @NotBlank
    private String name;
    private int npmScore;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy="topic")
    @JsonManagedReference
    private List<Survey> surveys;

    protected Topic() {}

    public Topic(@JsonProperty("name") String name, @JsonProperty("npmScore") int npmScore) {
        this.name = name;
        this.npmScore = npmScore;
        surveys = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public long getTopicId() {
        return topicId;
    }

    public int getNpmScore() {
        return npmScore;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setNpmScore(int npmScore) {
        this.npmScore = npmScore;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
