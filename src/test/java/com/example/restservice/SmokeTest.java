package com.example.restservice;

import com.example.restservice.controller.SurveyController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private SurveyController surveyController;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(surveyController).isNotNull();
    }
}
