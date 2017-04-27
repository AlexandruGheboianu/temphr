package com.ghb.temphr.integration;

import com.ghb.temphr.service.business.model.EmployeeSkill;
import com.ghb.temphr.service.business.repository.EmployeeSkillRepository;
import org.hashids.Hashids;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Alexandru Gheboianu on 08.03.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeExperienceTest extends AuthenticatedTest {

  private static final Hashids hashids = new Hashids("u8qb17mh8c");
  @Autowired
  private EmployeeSkillRepository employeeSkillRepository;

  @Test
  public void employeeSkillsReflectedInRead() throws Exception {
    MediaType json = MediaType.APPLICATION_JSON_UTF8;
    this.mvc
        .perform(get("/api/employees/" + hashids.encode(1) + "/skills").header("X-Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              assertTrue(contentAsString.split("},").length == 3);
            });

    this.mvc
        .perform(get("/api/employees/" + hashids.encode(134) + "/skills").header("X-Authorization", "Bearer " + token))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(json));
  }

  @Test
  public void employeeSkillsRetrieved() {
    List<EmployeeSkill> employeeSkills = employeeSkillRepository.findByEmployee_id(1);
    assertEquals(3, employeeSkills.size());
    employeeSkills.forEach(employeeSkill -> {
      assertEquals(1, employeeSkill.getEmployee().getId());
    });

    EmployeeSkill employeeSkill = new EmployeeSkill();
    employeeSkill.setEmployee(employeeSkills.get(0).getEmployee());
    employeeSkill.setSkill(employeeSkills.get(0).getSkill());
    employeeSkill.setLevel(9);
    employeeSkillRepository.save(employeeSkill);
    employeeSkills = employeeSkillRepository.findByEmployee_id(1);
    assertEquals(4, employeeSkills.size());
  }
}
