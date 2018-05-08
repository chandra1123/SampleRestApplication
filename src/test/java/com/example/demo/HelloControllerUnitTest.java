package com.example.demo;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebservicestestApplication.class)
@WebAppConfiguration
public class HelloControllerUnitTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenGetWithNamea_thenReturnsOKandExpectedString() throws Exception {
        final String userName = "TestUser";
        this.mockMvc.perform(get("/hello/" + userName))
            .andExpect(status().isOk())
            .andExpect(content().string(is("Hello " + userName)));
    }

    @Test
    public void whenGetWithNameContainingSpecialChar_thenReturnsFailed() throws Exception {
        final String userName = "TestUser /1";
        this.mockMvc.perform(get("/hello/" + userName))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenPostWithNamea_thenReturnsOKandExpectedString() throws Exception {
        final String userName = "TestUser";
        this.mockMvc.perform(post("/hello").content(userName))
            .andExpect(status().isOk())
            .andExpect(content().string(is("Hola " + userName)));
    }

    @Test
    public void whenPostWithNameContainingSpecialChar__thenReturnsOKandExpectedString() throws Exception {
        final String userName = "TestUser /1";
        this.mockMvc.perform(post("/hello/").content(userName))
            .andExpect(status().isOk())
            .andExpect(content().string(is("Hola " + userName)));
    }

}
