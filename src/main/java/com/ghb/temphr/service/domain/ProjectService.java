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

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by alexg on 3/8/2017.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private static final Hashids hashids = new Hashids("e7rq4kjiof");

    public void addProject(ProjectAdd projectAdd) {
        Project project = new Project();
        project.setName(projectAdd.getName());
        try {
            project.setStartDate(sdf.parse(projectAdd.getStartDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        projectRepository.save(project);
    }

    public Page<ProjectListModel> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(this::getProjectListModel);
    }

    public ProjectListModel getProject(String id) {
        return getProjectListModel(projectRepository.findOne(hashids.decode(id)[0]));
    }

    private ProjectListModel getProjectListModel(Project project) {
        ProjectListModel projectListModel = new ProjectListModel();
        projectListModel.setId(hashids.encode(project.getId()));
        projectListModel.setName(project.getName());
        projectListModel.setStartDate(sdf.format(project.getStartDate()));
        return projectListModel;
    }
}
