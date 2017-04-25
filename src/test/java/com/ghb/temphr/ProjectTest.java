package com.ghb.temphr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghb.temphr.service.domain.model.Project;
import com.ghb.temphr.service.domain.repository.ProjectRepository;
import org.hashids.Hashids;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by agheboianu on 24.04.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProjectTest extends AuthenticatedTest {
  private static final Hashids hashids = new Hashids("e7rq4kjiof");
  private static final MediaType json = MediaType.APPLICATION_JSON_UTF8;

  @Autowired
  private ProjectRepository projectRepository;

  @Test
  public void projectsReflectedInRead() throws Exception {
    this.mvc
        .perform(get("/api/projects").header("X-Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              assertTrue(contentAsString.split("},").length == 4);
            });
  }

  @Test
  public void projectByIdReflectedInRead() throws Exception {
    this.mvc
        .perform(get("/api/projects/" + hashids.encode(1)).header("X-Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();

              ObjectMapper mapper = new ObjectMapper();
              JsonNode actualObj = mapper.readTree(contentAsString);
              assertEquals(hashids.encode(1), actualObj.get("id").asText());
              assertEquals("Continental", actualObj.get("name").asText());
              assertEquals("03/10/2017", actualObj.get("startDate").asText());
            });
    this.mvc
        .perform(get("/api/projects/" + hashids.encode(123)).header("X-Authorization", "Bearer " + token))
        .andExpect(status().isNotFound());
  }

  @Test
  public void projectReflectedInCreate() throws Exception {
    HttpHeaders headers = getHttpHeaders();
    this.mvc
        .perform(post("/api/projects/").headers(headers).content("{\"name\":\"Plastor\",\"startDate\":\"01/01/2017\"}"))
        .andExpect(status().isCreated());

    Project proj = projectRepository.findOneByName("Plastor");
    assertNotNull(proj);
    projectRepository.delete(proj);
  }

  @Test
  public void projectDeleteForbidden() throws Exception {
    this.mvc
        .perform(delete("/api/projects/" + hashids.encode(1)).header("X-Authorization", "Bearer " + token))
        .andExpect(status().isForbidden());
  }

}
