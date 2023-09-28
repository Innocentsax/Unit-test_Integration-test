package com.example.mockito;

import com.example.mockito.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    private MockMvc mockMvc;
    @Autowired
    public UserControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET,"/user/1")
                .accept(MediaType.APPLICATION_JSON);
    ResultActions resultActions = mockMvc.perform(requestBuilder);
    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        Users user = Users.builder()
                .id(null)
                .userIdentificationNo("")
                .email("Egbon@adugbo.com")
                .gender("MF")
                .name("Egbon Abgero")
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST,"/save")
                .accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "bearer jwttokn3444434")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user));
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
