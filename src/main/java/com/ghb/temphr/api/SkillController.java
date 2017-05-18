package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.SkillAdd;
import com.ghb.temphr.api.apimodel.list.SkillListModel;
import com.ghb.temphr.api.apimodel.update.SkillUpdate;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import com.ghb.temphr.service.domain.SkillService;
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

import java.util.Optional;

/**
 * Created by alindobai on 5/18/17.
 */

@RestController
@RequestMapping("/api/skills")
public class SkillController {
  @Autowired
  private SkillService skillService;

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

}