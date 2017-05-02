package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.ProjectAdd;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.api.exception.ResourceNotFoundException;
import com.ghb.temphr.service.domain.ProjectService;
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
 * Created by alexg on 3/8/2017.
 */

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public Page<ProjectListModel> projects(Pageable pageable) {
    return projectService.getProjects(pageable);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ProjectListModel project(@PathVariable String id) {
    Optional<ProjectListModel> project = projectService.getProject(id);

    if (!project.isPresent()) {
      throw new ResourceNotFoundException("No project with id " + id + " was found.");
    }

    return project.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('ROLE_PREMIUM_MEMBER','ROLE_ADMIN')")
  public ResponseEntity addProject(@RequestBody @Validated ProjectAdd projectAdd) {
    projectService.addProject(projectAdd);
    return new ResponseEntity(HttpStatus.CREATED);
  }


  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity deleteProject(@PathVariable String id) {
    projectService.removeProject(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
