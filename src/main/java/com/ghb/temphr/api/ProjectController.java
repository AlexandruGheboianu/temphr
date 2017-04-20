package com.ghb.temphr.api;

import com.ghb.temphr.api.apimodel.create.ProjectAdd;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.service.domain.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alexg on 3/8/2017.
 */

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<ProjectListModel> projects(Pageable pageable) {
        return projectService.getProjects(pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ProjectListModel project(@PathVariable String id) {
        return projectService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addProject(@RequestBody @Validated ProjectAdd projectAdd) {
        projectService.addProject(projectAdd);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
