package com.ghb.temphr.service.business.repository;

import com.ghb.temphr.service.business.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by agheboianu on 07.03.2017.
 */
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
  List<EmployeeSkill> findByEmployee_id(long id);

  //added
  List<EmployeeSkill> findBySkill_id(long id);
  //
}
