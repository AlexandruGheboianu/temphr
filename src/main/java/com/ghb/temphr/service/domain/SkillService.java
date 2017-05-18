package com.ghb.temphr.service.domain;

import com.ghb.temphr.api.apimodel.create.SkillAdd;
import com.ghb.temphr.api.apimodel.list.SkillListModel;
import com.ghb.temphr.api.apimodel.update.SkillUpdate;
import com.ghb.temphr.service.domain.model.Skill;
import com.ghb.temphr.service.domain.model.enumerated.SkillType;
import com.ghb.temphr.service.domain.repository.SkillRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by agheboianu on 07.03.2017.
 */

@Service
public class SkillService {

  private static final Hashids hashids = new Hashids("e7rq4kjiof");

  @Autowired
  private SkillRepository skillRepository;

  public void addSkill(SkillAdd skillAdd) {
    Skill skill = new Skill();
    skill.setName(skillAdd.getName());
    skill.setSkillType(SkillType.valueOf(skillAdd.getSkillType()));

    skillRepository.save(skill);
  }

  // added by Alin
  public void updateSkill(SkillUpdate skillUpdate, String id) {
    Skill skill = skillRepository.findOne(hashids.decode(id)[0]);
    if (skillUpdate.getName() != null) {
      skill.setName(skillUpdate.getName());
    }
    if (skillUpdate.getSkillType() != null) {
      skill.setSkillType(SkillType.valueOf(skillUpdate.getSkillType()));

      skillRepository.save(skill);
    }
    // end
  }

  public Page<SkillListModel> listSkills(Pageable pageable) {
    return skillRepository.findAll(pageable).map(this::getSkillListModel);
  }

  public Optional<SkillListModel> getSkill(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length == 0) {
      return Optional.empty();
    }

    Skill skill = skillRepository.findOne(decodedIds[0]);
    if (skill == null) {
      return Optional.empty();
    }
    return Optional.of(getSkillListModel(skill));
  }

  private SkillListModel getSkillListModel(Skill skill) {
    SkillListModel skillListModel = new SkillListModel();
    skillListModel.setId(hashids.encode(skill.getId()));
    skillListModel.setName(skill.getName());
    skillListModel.setSkillType(skill.getSkillType().name());
    return skillListModel;
  }

  public void removeSkill(String id) {
    long[] decodedIds = hashids.decode(id);

    if (decodedIds.length > 0) {
      Skill skill = skillRepository.findOne(hashids.decode(id)[0]);
      skill.setDeleted(true);
      skillRepository.save(skill);
    }
  }
}