package com.ghb.temphr.service.business;

import com.ghb.temphr.api.apimodel.list.EmployeeSkillListModel;
import com.ghb.temphr.service.business.model.EmployeeSkill;
import com.ghb.temphr.service.business.repository.EmployeeSkillRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by agheboianu on 07.03.2017.
 */
@Service
public class EmployeeExperienceService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    private static final Hashids hashids = new Hashids("u8qb17mh8c");


    public List<EmployeeSkillListModel> getEmployeeSkills(String id) {
        return employeeSkillRepository.findByEmployee_id(hashids.decode(id)[0])
                .stream()
                .map(this::getEmployeeSkillListModel)
                .collect(Collectors.toList());
    }

    private EmployeeSkillListModel getEmployeeSkillListModel(EmployeeSkill employeeSkill) {
        EmployeeSkillListModel employeeSkillListModel = new EmployeeSkillListModel();
        employeeSkillListModel.setId(hashids.encode(employeeSkill.getId()));
        employeeSkillListModel.setName(employeeSkill.getSkill().getName());
        employeeSkillListModel.setType(employeeSkill.getSkill().getSkillType().name());
        employeeSkillListModel.setLevel(employeeSkill.getLevel());
        return employeeSkillListModel;
    }
}
