package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by alexg on 3/8/2017.
 */

@Getter
@Setter
@Entity
@Table(name = "PROJECTS")
public class Project extends BaseEntity {

    private String name;

    private Date startDate;
}
