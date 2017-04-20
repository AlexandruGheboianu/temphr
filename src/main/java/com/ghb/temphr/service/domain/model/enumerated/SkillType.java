package com.ghb.temphr.service.domain.model.enumerated;

/**
 * Created by agheboianu on 07.03.2017.
 */
public enum SkillType {
    BACK_END("Back End"), FRONT_END("Front End");

    SkillType(String name) {
        this.name = name;
    }

    private String name;

    private String stringValue() {
        return name;
    }
}
