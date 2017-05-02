package com.ghb.temphr.service.domain;

import com.ghb.temphr.api.apimodel.create.ProjectAdd;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.service.domain.model.Project;
import com.ghb.temphr.service.domain.repository.ProjectRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by alexg on 3/8/2017.
 */
@Service
public class ProjectService {

  private static final Hashids hashids = new Hashids("e7rq4kjiof");

  @Autowired
  private ProjectRepository projectRepository;

  public void addProject(ProjectAdd projectAdd) {
    Project project = new Project();
    project.setName(projectAdd.getName());
    project.setStartDate(projectAdd.getStartDate());

    projectRepository.save(project);
  }

  public Page<ProjectListModel> getProjects(Pageable pageable) {
    return projectRepository.findAll(pageable).map(this::getProjectListModel);
  }

  public Optional<ProjectListModel> getProject(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    Project project = projectRepository.findOne(decodedIds[0]);
    if (project == null) {
      return Optional.empty();
    }
    return Optional.of(getProjectListModel(project));
  }

  private ProjectListModel getProjectListModel(Project project) {
    ProjectListModel projectListModel = new ProjectListModel();
    projectListModel.setId(hashids.encode(project.getId()));
    projectListModel.setName(project.getName());
    projectListModel.setStartDate(project.getStartDate());
    return projectListModel;
  }

  public void removeProject(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length > 0) {
      Project project = projectRepository.findOne(hashids.decode(id)[0]);
      project.setDeleted(true);
      projectRepository.save(project);
    }
  }
}
