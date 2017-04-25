package com.ghb.temphr.service.business.model;

import com.ghb.temphr.service.common.model.BaseEntity;
import com.ghb.temphr.service.domain.model.Employee;
import com.ghb.temphr.service.domain.model.Skill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by agheboianu on 07.03.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE_SKILLS")
public class EmployeeSkill extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "skill_id")
  private Skill skill;

  private int level;
}
