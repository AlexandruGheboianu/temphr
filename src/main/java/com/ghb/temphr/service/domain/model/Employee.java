package com.ghb.temphr.service.domain.model;

import com.ghb.temphr.service.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by agheboianu on 03.03.2017.
 */

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
@Where(clause = "deleted='false'")
public class Employee extends BaseEntity {

  @Column(length = 50, nullable = false)
  private String firstName;

  @Column(length = 50, nullable = false)
  private String lastName;

  @Column(length = 50, nullable = false)
  private String email;

  private boolean deleted;
}
