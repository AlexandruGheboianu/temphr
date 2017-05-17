package com.ghb.temphr.service.business;

import com.ghb.temphr.api.apimodel.list.EmployeeSkillListModel;
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

  private EmployeeSkillListModel getEmployeeSkillListModel(EmployeeSkill employeeSkill) {
    EmployeeSkillListModel employeeSkillListModel = new EmployeeSkillListModel();
    employeeSkillListModel.setId(hashids.encode(employeeSkill.getId()));
    employeeSkillListModel.setName(employeeSkill.getSkill().getName());
    employeeSkillListModel.setType(employeeSkill.getSkill().getSkillType().name());
    employeeSkillListModel.setLevel(employeeSkill.getLevel());
    return employeeSkillListModel;
  }
}
