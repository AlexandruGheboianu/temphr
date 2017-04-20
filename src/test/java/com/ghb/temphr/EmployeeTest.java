package com.ghb.temphr;

/**
 * Created by Alexandru Gheboianu on 07.03.2017.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.hashids.Hashids;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class EmployeeTest extends AuthenticatedTest{
    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Hashids hashids = new Hashids("k7ds8kxomx");

    @Test
    public void employeesReflectedInRead() throws Exception {
        MediaType json = MediaType.APPLICATION_JSON_UTF8;
        this.mvc
                .perform(get("/api/employees").header("X-Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(json))
                .andExpect(
                        mvcResult -> {
                            String contentAsString = mvcResult.getResponse().getContentAsString();
                            assertTrue(contentAsString.split("},").length == 10);
                        });
    }

    @Test
    public void employeeByIdReflectedInRead() throws Exception {
        MediaType json = MediaType.APPLICATION_JSON_UTF8;
        this.mvc
                .perform(get("/api/employees/" + hashids.encode(1)).header("X-Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(json))
                .andExpect(
                        mvcResult -> {
                            String contentAsString = mvcResult.getResponse().getContentAsString();

                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode actualObj = mapper.readTree(contentAsString);
                            assertEquals(hashids.encode(1), actualObj.get("id").asText());
                            assertEquals("Alex", actualObj.get("firstName").asText());
                            assertEquals("Pope", actualObj.get("lastName").asText());
                            assertEquals("apope@example.com", actualObj.get("email").asText());
                        });
    }
}
