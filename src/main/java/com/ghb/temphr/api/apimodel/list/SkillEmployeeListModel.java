package com.ghb.temphr.api.apimodel.list;

import com.ghb.temphr.service.domain.model.Skill;
import com.ghb.temphr.service.domain.model.enumerated.SkillType;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by alindobai on 5/18/17.
 */


@Getter
@Setter
public class SkillEmployeeListModel extends EmployeeListModel {
  private String skillName;
  private String skillType;
}
