package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.SkillAdd;
import com.ghb.temphr.api.apimodel.list.EmployeeSkillListModel;
import com.ghb.temphr.api.apimodel.list.SkillEmployeeListModel;
import com.ghb.temphr.api.apimodel.list.SkillListModel;
import com.ghb.temphr.api.apimodel.update.SkillUpdate;
import com.ghb.temphr.api.exception.ParentResourceNotFoundException;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import com.ghb.temphr.service.business.EmployeeExperienceService;
import com.ghb.temphr.service.business.repository.EmployeeSkillRepository;
import com.ghb.temphr.service.domain.SkillService;
import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by alindobai on 5/18/17.
 */

@RestController
@RequestMapping("/api/skills")
public class SkillController {
  @Autowired
  private SkillService skillService;
  @Autowired
  private EmployeeExperienceService employeeExperienceService;



  /*
  private static final Hashids hashids = new Hashids("k7ds8kxomx");
  @Autowired
  private EmployeeSkillRepository employeeSkillRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  */


  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public Page<SkillListModel> skills(Pageable pageable) {
    return skillService.listSkills(pageable);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public SkillListModel skill(@PathVariable String id) {
    Optional<SkillListModel> skill = skillService.getSkill(id);

    if (!skill.isPresent()) {
      throw new ResourceNotFoundException("No skill with id " + id + " was found.");
    }

    return skill.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity addSkill(@RequestBody @Validated SkillAdd skillAdd) {
    skillService.addSkill(skillAdd);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  // added by alin
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity updateSkill(@RequestBody @Validated SkillUpdate skillUpdate, @PathVariable String id) {
    skillService.updateSkill(skillUpdate, id);
    return new ResponseEntity(HttpStatus.OK);
  }
  // end

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity deleteSkill(@PathVariable String id) {
    skillService.removeSkill(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  //added
  @RequestMapping(method = RequestMethod.GET, value = "/{id}/employees")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public List<SkillEmployeeListModel> skillEmployees(@PathVariable String id) {
    Optional<List<SkillEmployeeListModel>> skillEmployees = employeeExperienceService.getSkillEmployees(id);

    if (!skillEmployees.isPresent()) {
      throw new ParentResourceNotFoundException("/api/skills/" + id);
    }

    return skillEmployees.get();
  }
  //

  /*
  public Optional<List<EmployeeSkillListModel>> getEmployeeSkills(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    if (!employeeRepository.exists(decodedIds[0])) {
      return Optional.empty();
    }
    return Optional.of(employeeSkillRepository.findByEmployee_id(decodedIds[0])
            .stream()
            .map(this::getEmployeeSkillListModel)
            .collect(Collectors.toList()));
  }
  */
}