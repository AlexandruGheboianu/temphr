package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.domain.model.enumerated.SkillType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by agheboianu on 07.03.2017.
 */

@Getter
@Setter
@Entity
@Table(name = "SKILLS")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SkillType skillType;
}
