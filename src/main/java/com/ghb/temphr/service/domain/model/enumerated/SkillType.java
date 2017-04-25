package com.ghb.temphr.service.domain.model.enumerated;

/**
 * Created by agheboianu on 07.03.2017.
 */
public enum SkillType {
  BACK_END("Back End"), FRONT_END("Front End");

  private String name;

  SkillType(String name) {
    this.name = name;
  }

}
