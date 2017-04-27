package com.ghb.temphr.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghb.temphr.service.common.model.User;
import com.ghb.temphr.service.common.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by agheboianu on 24.04.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SecurityTest {
  private static final MediaType json = MediaType.APPLICATION_JSON_UTF8;
  @Autowired
  protected MockMvc mvc;

  @Autowired
  protected UserRepository userRepository;

  @Test
  public void wrongPasswordTest() throws Exception {
    ResultActions perform = attemptLogin("alexghebo", "123334");
    perform.andExpect(status().isUnauthorized());
  }

  private ResultActions attemptLogin(String user, String password) throws Exception {
    MediaType json = MediaType.APPLICATION_JSON;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    return this.mvc
        .perform(post("/api/auth/login").headers(headers).content("{\"username\":\"" + user + "\",\"password\":\"" + password + "\"}"));
  }

  @Test
  public void notSupportedMethodTest() throws Exception {
    MediaType json = MediaType.APPLICATION_JSON;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    this.mvc
        .perform(put("/api/auth/login").headers(headers).content("{\"username\":\"alexghebo\",\"password\":\"test1234\"}"))
        .andExpect(status().isUnauthorized());


  }

  @Test
  public void noPermissionsTest() throws Exception {
    MediaType json = MediaType.APPLICATION_JSON;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    this.mvc
        .perform(post("/api/auth/login").headers(headers).content("{\"username\":\"gigi\",\"password\":\"test1234\"}"))
    .andExpect(status().isUnauthorized());
  }

  @Test
  public void noUsernameTest() throws Exception {
    MediaType json = MediaType.APPLICATION_JSON;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("X-Requested-With", "XMLHttpRequest");
    this.mvc
        .perform(post("/api/auth/login").headers(headers).content("{\"username\":\"\",\"password\":\"test1234\"}"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void expiredToken() throws Exception {
    this.mvc
        .perform(get("/api/employees").header("X-Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4Z2hlYm8iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfUFJFTUlVTV9NRU1CRVIiXSwiaXNzIjoiaHR0cDovL2doYi1zb2Z0d2FyZS5jb20iLCJpYXQiOjE0OTMyOTM0NzMsImV4cCI6MTQ5MzI5NDM3M30.XWPxssFYGhoWDJfOC4AJuD-R2T5OrInUANFEck8cM_rm44sbr3eYFTP5R1YKC3oVxcEQ2KzVrkcNjeJok3TW9g"))
        .andExpect(status().isUnauthorized());

  }
  @Test
  public void malformedToken() throws Exception {
    this.mvc
        .perform(get("/api/employees").header("X-Authorization", "Beare eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4Z2hlYm8iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfUFJFTUlVTV9NRU1CRVIiXSwiaXNzIjoiaHR0cDovL2doYi1zb2Z0d2FyZS5jb20iLCJpYXQiOjE0OTMwMzkxMjUsImV4cCI6MTQ5MzA0MDAyNX0.V90rPOV5Ff8z_XcmEF7PKptJNLNxqTwHNDJbFuoTVErtLqFcLdXaUBIG6keYMaDFPUg_YCC7LF1q1E_P6qPWlw"))
        .andExpect(status().isUnauthorized());

    this.mvc
        .perform(get("/api/employees").header("X-Authorization", ""))
        .andExpect(status().isUnauthorized());

    this.mvc
        .perform(get("/api/employees"))
        .andExpect(status().isUnauthorized());

    this.mvc
        .perform(get("/api/employees").header("X-Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4Z2hl3m8iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfUFJFTUlVTV9NRU1CRVIiXSwiaXNzIjoiaHR0cDovL2doYi1zb2Z0d2FyZS5jb20iLCJpYXQiOjE0OTMwMzkxMjUsImV4cCI6MTQ5MzA0MDAyNX0.V90rPOV5Ff8z_XcmEF7PKptJNLNxqTwHNDJbFuoTVErtLqFcLdXaUBIG6keYMaDFPUg_YCC7LF1q1E_P6qPWlw"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void refreshTokenSuccess() throws Exception {
    //log in and obtain the refresh token
    final String[] token = new String[1];
    ResultActions perform = attemptLogin("alexghebo", "test1234");
    perform.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              ObjectMapper mapper = new ObjectMapper();
              token[0] = mapper.readTree(contentAsString).get("refreshToken").asText();
            });
    // request a new token by sending the refresh token to the refresh endpoint
    this.mvc
        .perform(get("/api/auth/token").header("X-Authorization", "Bearer " + token[0]))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              ObjectMapper mapper = new ObjectMapper();
              token[0] = mapper.readTree(contentAsString).get("token").asText();
              assertNotNull(token[0]);
            });

    // check if the new token is valid
    this.mvc
        .perform(get("/api/employees").header("X-Authorization", "Bearer " + token[0]))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              assertTrue(contentAsString.split("},").length == 10);
            });
  }

  @Test
  @Transactional
  public void invalidRefreshToken() throws Exception {
    //log in and obtain the refresh token
    final String[] token1 = new String[1];
    ResultActions perform1 = attemptLogin("alexghebo", "test1234");
    perform1.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              ObjectMapper mapper = new ObjectMapper();
              token1[0] = mapper.readTree(contentAsString).get("refreshToken").asText();
            });


    User user = userRepository.findByUsername("alexghebo").get();
    user.getRoles().clear();
    userRepository.save(user);
    this.mvc
        .perform(get("/api/auth/token").header("X-Authorization", "Bearer " + token1[0]))
        .andExpect(status().isForbidden());
  }
}
