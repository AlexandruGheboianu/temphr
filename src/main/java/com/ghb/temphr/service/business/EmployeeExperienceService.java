package com.ghb.temphr.service.business;

import com.ghb.temphr.api.apimodel.list.EmployeeSkillListModel;
import com.ghb.temphr.api.apimodel.list.ProjectListModel;
import com.ghb.temphr.api.apimodel.list.SkillEmployeeListModel;
import com.ghb.temphr.service.business.model.EmployeeSkill;
import com.ghb.temphr.service.business.repository.EmployeeSkillRepository;
import com.ghb.temphr.service.domain.repository.EmployeeRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by agheboianu on 07.03.2017.
 */
@Service
public class EmployeeExperienceService {

  private static final Hashids hashids = new Hashids("k7ds8kxomx");

  private static final Hashids hashidsSkills = new Hashids("e7rq4kjiof");

  @Autowired
  private EmployeeSkillRepository employeeSkillRepository;
  @Autowired
  private EmployeeRepository employeeRepository;

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
  // added
  public Optional<List<SkillEmployeeListModel>> getSkillEmployees(String id) {
    long[] decodedIds = hashidsSkills.decode(id);

    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    if (!employeeRepository.exists(decodedIds[0])) {
      return Optional.empty();
    }
    return Optional.of(employeeSkillRepository.findBySkill_id(decodedIds[0])
            .stream()
            .map(this::getSkillEmployeeListModel)
            .collect(Collectors.toList()));
  }
  //

  private EmployeeSkillListModel getEmployeeSkillListModel(EmployeeSkill employeeSkill) {
    EmployeeSkillListModel employeeSkillListModel = new EmployeeSkillListModel();
    employeeSkillListModel.setId(hashids.encode(employeeSkill.getId()));
    employeeSkillListModel.setName(employeeSkill.getSkill().getName());
    employeeSkillListModel.setSkillType(employeeSkill.getSkill().getSkillType().name());
    employeeSkillListModel.setLevel(employeeSkill.getLevel());
    return employeeSkillListModel;
  }


  private SkillEmployeeListModel getSkillEmployeeListModel(EmployeeSkill employeeSkill) {
    SkillEmployeeListModel skillEmployeeListModel = new SkillEmployeeListModel();
    skillEmployeeListModel.setSkillName(employeeSkill.getSkill().getName());
    skillEmployeeListModel.setSkillType(employeeSkill.getSkill().getSkillType().name());
    skillEmployeeListModel.setId(hashids.encode(employeeSkill.getId()));
    skillEmployeeListModel.setFirstName(employeeSkill.getEmployee().getFirstName());
    skillEmployeeListModel.setLastName(employeeSkill.getEmployee().getLastName());
    skillEmployeeListModel.setEmail(employeeSkill.getEmployee().getEmail());
    return skillEmployeeListModel;
  }
}
