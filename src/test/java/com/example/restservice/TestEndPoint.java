package com.example.restservice;

import com.example.restservice.model.Survey;
import com.example.restservice.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEndPoint {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_CreateSurvey_When_ValidRequest() throws Exception {
        this.mockMvc.perform(post("/createSurveys")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{ \"question\": \"How likely recommend to a friend?\", \"topic\": { \"name\": \"Groovy\" } }"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_GetSurveys_When_ValidRequest() throws Exception {
        this.mockMvc.perform(get("/surveys")).andDo(print()).andExpect(status().isOk());
    }
}
