package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.domain.model.enumerated.SkillType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by agheboianu on 07.03.2017.
 */

@Getter
@Setter
@Entity
@Table(name = "SKILLS")
@Where(clause = "deleted='false'")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "skill_type")
  private SkillType skillType;

  private boolean deleted;
}
