package com.ghb.temphr.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by agheboianu on 20.04.2017.
 */
public abstract class AuthenticatedTest {
  @Autowired
  protected MockMvc mvc;
  protected String token;

  @Before
  public void login() throws Exception {
    MediaType json = MediaType.APPLICATION_JSON;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    this.mvc
        .perform(post("/api/auth/login").headers(headers).content("{\"username\":\"alexghebo\",\"password\":\"test1234\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              ObjectMapper mapper = new ObjectMapper();
              token = mapper.readTree(contentAsString).get("token").asText();
            });
  }


  protected HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    headers.add("X-Authorization", "Bearer " + token);
    return headers;
  }
}
